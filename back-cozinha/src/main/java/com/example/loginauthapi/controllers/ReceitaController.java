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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginauthapi.Services.ReceitaService;
import com.example.loginauthapi.dto.ReceitasRequestDTO;
import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.model.Receitas;
import com.example.loginauthapi.repositories.FuncionarioRepository;
import com.example.loginauthapi.repositories.ReceitasRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    
    @Autowired
    private ReceitaService receitaService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ReceitasRepository receitasRepository;
    
    @GetMapping("/")
    public List<Receitas> List(){
        return receitaService.List();
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody @Valid ReceitasRequestDTO body){
        Funcionario funcionario = findByIdCozinheiro(body.cozinheiro());

        Receitas receitas = new Receitas();
        receitas.setNome(body.nome());
        receitas.setData_inclusao(new Date());
        receitas.setCozinheiro(funcionario);
        receitas.setDescricao(body.descricao());
        receitas.setModo_preparo(body.modo_preparo());
        receitas.setNum_porcao(body.num_porcao());
        receitas.setInd_inedita(body.ind_inedita());
        receitasRepository.save(receitas);
        return ResponseEntity.ok().build();
    } 

    @PutMapping("/")
    public Receitas update(@RequestBody @Valid Receitas receitas){
        return receitaService.update(receitas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
         receitaService.delete(id);
         return ResponseEntity.ok().build();
    }

    private Funcionario findByIdCozinheiro(Long cozinheiro){
       return  funcionarioRepository.findById(cozinheiro).orElseThrow(()-> new RuntimeException("cargo não encontrado"));
    }
}