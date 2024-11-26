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
    public ResponseEntity create(@RequestBody @Valid Avaliacao avaliacao, @RequestHeader("Authorization") String token) {
        String email = tokenService.validateToken(token.replace("Bearer ", ""));

        if (email == null) {
            return ResponseEntity.status(401).body("Token inválido ou expirado");
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        Funcionario degustador = funcionarioRepository.findByUser(user)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        Receitas receita = receitasRepository.findById(avaliacao.getReceita().getId_receita())
            .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        avaliacao.setDegustador(degustador);
        avaliacao.setData_degustacao(new Date());
        avaliacao.setReceita(receita);

        avaliacaoService.create(avaliacao);

        return ResponseEntity.ok("Avaliação adicionada com sucesso!");
    } 

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid AvaliacaoRequestDTO body,@PathVariable("id") Long id){
       avaliacaoService.update(id,body);
        return ResponseEntity.ok("Avaliação editada!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
       avaliacaoService.delete(id);
        return ResponseEntity.ok("Avaliação deletada!!");
    }
}
