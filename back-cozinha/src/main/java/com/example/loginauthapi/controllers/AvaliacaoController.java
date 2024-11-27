package com.example.loginauthapi.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginauthapi.Services.AvaliacaoService;
import com.example.loginauthapi.Services.UserServices;
import com.example.loginauthapi.dto.AvaliacaoRequestDTO;
import com.example.loginauthapi.dto.AvaliacaoResponseDTO;
import com.example.loginauthapi.dto.ReceitasRequestDTO;
import com.example.loginauthapi.infra.security.TokenService;
import com.example.loginauthapi.model.Avaliacao;
import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.model.Receitas;
import com.example.loginauthapi.model.User;
import com.example.loginauthapi.repositories.FuncionarioRepository;
import com.example.loginauthapi.repositories.ReceitasRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServices userService;

    @GetMapping("/")
    public List<AvaliacaoResponseDTO> List(){
        return avaliacaoService.List();
    }

    @PostMapping("/")
    public ResponseEntity<String> create(
            @RequestBody @Valid AvaliacaoRequestDTO body, 
            @RequestHeader("Authorization") String token) {

        // Validação do token
        String email = tokenService.validateToken(token.replace("Bearer ", ""));
        if (email == null) {
            return ResponseEntity.status(401).body("Token inválido ou expirado");
        }

        // Buscando o usuário
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        // Buscando o funcionário (degustador)
        Funcionario degustador = funcionarioRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Buscando a receita associada à avaliação
        Receitas receita = receitasRepository.findById(body.id_receita())
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        // Criando a avaliação com o DTO (o serviço vai tratar de salvar a avaliação)
        avaliacaoService.create(body, degustador, receita);

        return ResponseEntity.ok("Avaliação adicionada com sucesso!");
    }

    // Endpoint para atualizar uma avaliação existente
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @RequestBody @Valid AvaliacaoRequestDTO body, 
            @PathVariable("id") Long id) {
        // Atualizando a avaliação
        avaliacaoService.update(id, body);
        return ResponseEntity.ok("Avaliação editada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
       avaliacaoService.delete(id);
        return ResponseEntity.ok("Avaliação deletada!!");
    }
}
