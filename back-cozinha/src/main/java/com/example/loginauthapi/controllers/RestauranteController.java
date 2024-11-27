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

import com.example.loginauthapi.Services.RestauranteService;
import com.example.loginauthapi.dto.RestauranteRequestDTO;
import com.example.loginauthapi.dto.RestauranteResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/")
    public List<RestauranteResponseDTO> list(){
        return restauranteService.List();
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody @Valid RestauranteRequestDTO body) {
        restauranteService.create(body);
        return ResponseEntity.status(201).body("Restaurante criado com sucesso!");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid RestauranteRequestDTO body,@PathVariable("id") Long id) {
        restauranteService.update(body,id);
        return ResponseEntity.status(201).body("Restaurante editado com sucesso!");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id) {
        restauranteService.delete(id);
        return ResponseEntity.status(201).body("Restaurante deletado com sucesso!");
    }
    
}
