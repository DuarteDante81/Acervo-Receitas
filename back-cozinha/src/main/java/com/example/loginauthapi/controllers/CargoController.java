package com.example.loginauthapi.controllers;

import com.example.loginauthapi.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.loginauthapi.Services.CargoService;
import com.example.loginauthapi.model.Cargo;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/")
    public List<Cargo> List(){
        return cargoService.List();
    }

    @PostMapping
    public Cargo create(@RequestBody @Valid Cargo cargo){
        return cargoService.create(cargo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        cargoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
