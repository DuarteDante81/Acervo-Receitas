package com.example.loginauthapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginauthapi.Services.CategoriaService;
import com.example.loginauthapi.model.Categoria;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("/")
    public List<Categoria> List(){
        return categoriaService.List();
    }
    @PostMapping("/")
    public Categoria create(@RequestBody @Valid Categoria categoria){
        return categoriaService.create(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        categoriaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
