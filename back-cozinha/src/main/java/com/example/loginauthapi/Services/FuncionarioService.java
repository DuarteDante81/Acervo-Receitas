package com.example.loginauthapi.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    
    public Funcionario create(Funcionario funcionario){
        funcionario.setData_ade(new Date());
        Funcionario result = funcionarioRepository.saveAndFlush(funcionario);
        return result;
    }

    public List<Funcionario> list(){
        return funcionarioRepository.findAll();
    }

    
    public Funcionario update(Funcionario funcionario){
        funcionario.setData_ade(new Date());
        return funcionarioRepository.saveAndFlush(funcionario);  
    }

    public void delete(Long id){
       
        Funcionario funcionario = verificaFuncionario(id);
        egresso(id);
        funcionarioRepository.delete(funcionario);
        
    }

    public void egresso(Long id){
        Funcionario funcionario = verificaFuncionario(id);
        funcionario.setData_egresse(new Date());
        funcionarioRepository.save(funcionario);

    }

    public Funcionario verificaFuncionario(Long id){
        return funcionarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Funcionario não encontrado"));
    }
    
   
}
