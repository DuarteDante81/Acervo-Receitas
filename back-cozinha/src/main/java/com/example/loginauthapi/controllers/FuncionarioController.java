package com.example.loginauthapi.controllers;

import java.util.List;

import com.example.loginauthapi.dto.RegisterRequestDTO;

import com.example.loginauthapi.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginauthapi.Services.FuncionarioService;
import com.example.loginauthapi.model.Funcionario;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    

    @GetMapping("/")
    public List<Funcionario> List(){
        return funcionarioService.list();
    }

    @PostMapping("/")
    public Funcionario create(@RequestBody @Valid RegisterRequestDTO body,Funcionario funcionario){
        Funcionario result = funcionarioService.create(funcionario,body);
        return funcionarioRepository.save(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid RegisterRequestDTO body,@PathVariable("id") Long id){
     funcionarioService.update(id,body);
     return ResponseEntity.ok().build();
    }

    @PutMapping("/softDelete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable("id") Long id){
     funcionarioService.egresso(id);
     return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        funcionarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    
}
