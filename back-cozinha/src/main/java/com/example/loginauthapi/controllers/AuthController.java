package com.example.loginauthapi.controllers;

import com.example.loginauthapi.dto.LoginRequestDTO;
import com.example.loginauthapi.dto.RegisterRequestDTO;
import com.example.loginauthapi.dto.ResponseDTO;
import com.example.loginauthapi.infra.security.TokenService;
import com.example.loginauthapi.model.Cargo;
import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.model.User;
import com.example.loginauthapi.repositories.CargoRepository;
import com.example.loginauthapi.repositories.FuncionarioRepository;
import com.example.loginauthapi.repositories.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO body) {
        User user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Funcionario funcionario = funcionarioRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado para este usuário"));
        Cargo cargo = funcionario.getCargo();
        if (passwordEncoder.matches(body.senha(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(), cargo.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }



    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO body,Funcionario funcionario) {
        Optional<User> user = this.repository.findByEmail(body.email());
        
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setSenha(passwordEncoder.encode(body.senha()));
            newUser.setEmail(body.email());
            newUser.setNome(body.nome());
            this.repository.save(newUser);
            
            Cargo cargo = findByNome(body.nome_cargo());
    

            funcionario.setRg(body.rg());
            funcionario.setSalario(body.salario());
            funcionario.setUser(newUser);
            funcionario.setCargo(cargo); 
            funcionario.setNome(body.nome());
            funcionario.setData_ade(new Date());
            funcionarioRepository.save(funcionario);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok().build();
        
        }
        
        return ResponseEntity.badRequest().build();
    }

    private Cargo findByNome(String nome){
        return cargoRepository.findByNome(nome).orElseThrow(()-> new RuntimeException("nome do cargo não encontrado"));
    }

    
}