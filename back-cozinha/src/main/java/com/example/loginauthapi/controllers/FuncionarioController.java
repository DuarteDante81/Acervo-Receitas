package com.example.loginauthapi.controllers;

import java.util.Date;
import java.util.List;

import com.example.loginauthapi.dto.FuncionarioResponseDTO;
import com.example.loginauthapi.dto.RegisterRequestDTO;
import com.example.loginauthapi.model.Cargo;
import com.example.loginauthapi.repositories.CargoRepository;
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
    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping("/")
    public List<FuncionarioResponseDTO> list() {
        return funcionarioService.list(); 
    }

    @PostMapping("/")
    public Funcionario create(@RequestBody @Valid RegisterRequestDTO body){
        Cargo cargo = findByNome(body.nome_cargo());

        Funcionario funcionario = new Funcionario();
        funcionario.setRg(body.rg());
        funcionario.setSalario(body.salario());
        funcionario.setCargo(cargo);
        funcionario.setNome(body.nome());
        funcionario.setData_ade(new Date());
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario update(@PathVariable("id") Long id, @RequestBody @Valid RegisterRequestDTO body) {
        // Chama o serviço para atualizar os dados com base no id
        return funcionarioService.update(id, body);
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

    private Cargo findByNome(String nome){
        return cargoRepository.findByNome(nome).orElseThrow(()-> new RuntimeException("nome do cargo não encontrado"));
    }
}
