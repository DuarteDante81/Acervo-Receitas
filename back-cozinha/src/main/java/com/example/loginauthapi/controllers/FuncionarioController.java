package com.example.loginauthapi.controllers;

import java.util.List;


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

    @GetMapping("/")
    public List<Funcionario> List(){
        return funcionarioService.list();
    }
    @PostMapping("/")
    public Funcionario create(@RequestBody @Valid Funcionario funcionario){
        return funcionarioService.create(funcionario);
    } 
    @PutMapping("/")
    public Funcionario update(@RequestBody @Valid Funcionario funcionario){
        return funcionarioService.update(funcionario);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        funcionarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
