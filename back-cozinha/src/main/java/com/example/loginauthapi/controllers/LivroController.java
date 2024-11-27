package com.example.loginauthapi.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginauthapi.Services.LivroService;
import com.example.loginauthapi.Services.ReceitaService;
import com.example.loginauthapi.Services.UserServices;
import com.example.loginauthapi.dto.LivrosRequestDTO;
import com.example.loginauthapi.dto.LivrosResponseDTO;
import com.example.loginauthapi.infra.security.TokenService;
import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.model.Livros;
import com.example.loginauthapi.model.Receitas;
import com.example.loginauthapi.model.User;
import com.example.loginauthapi.repositories.FuncionarioRepository;
import com.example.loginauthapi.repositories.LivrosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServices userService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private ReceitaService receitaService;
    
    @Autowired
    private LivrosRepository livrosRepository;
    
    @Autowired
    private LivroService livroService;

    @GetMapping("/")
    public ResponseEntity<List<LivrosResponseDTO>> list() {
        List<LivrosResponseDTO> livros = livroService.list();
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody @Valid LivrosRequestDTO body, @RequestHeader("Authorization") String token) {  
        String email = tokenService.validateToken(token.replace("Bearer ", ""));
        if (email == null) {
            return ResponseEntity.status(401).body("Token inválido ou expirado");
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        Funcionario funcionario = funcionarioRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        Livros livro = new Livros();
        livro.setTitulo(body.titulo());
        livro.setCod_isbn(body.cod_isbn());
        livro.setData_criacao(new Date());
        livro.setEditor(funcionario); 

        if(body.cod_isbn() != null && !body.cod_isbn().isEmpty()){
            livro.setPublicado("Sim");
        }else{
            livro.setPublicado("Não");
        }

        if (body.receitasIds() != null && !body.receitasIds().isEmpty()) {
            List<Receitas> receitas = receitaService.findByIds(body.receitasIds());
            livro.setReceitas(receitas);  
        }

        livrosRepository.save(livro);

        return ResponseEntity.status(201).body("Livro criado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid LivrosRequestDTO body, @RequestHeader("Authorization") String token) {
        String email = tokenService.validateToken(token.replace("Bearer ", ""));
        if (email == null) {
            return ResponseEntity.status(401).body("Token inválido ou expirado");
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        Funcionario funcionario = funcionarioRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        Livros livro = livrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setTitulo(body.titulo());
        livro.setCod_isbn(body.cod_isbn());
        livro.setData_criacao(new Date());  
        livro.setEditor(funcionario);

        if (body.cod_isbn() != null && !body.cod_isbn().isEmpty()) {
            livro.setPublicado("Sim");
        } else {
            livro.setPublicado("Não");
        }

        if (body.receitasIds() != null && !body.receitasIds().isEmpty()) {
            List<Receitas> receitas = receitaService.findByIds(body.receitasIds());
            livro.setReceitas(receitas);
        } else {
            livro.setReceitas(null);
        }

        livrosRepository.save(livro);

        return ResponseEntity.status(200).body("Livro atualizado com sucesso");
    }
}
