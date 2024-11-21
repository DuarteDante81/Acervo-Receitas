package com.example.loginauthapi.Services;

import java.util.Date;
import java.util.List;

import com.example.loginauthapi.dto.RegisterRequestDTO;
import com.example.loginauthapi.model.Cargo;
import com.example.loginauthapi.model.User;
import com.example.loginauthapi.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private CargoRepository cargoRepository;

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


    public Funcionario update(Long id, RegisterRequestDTO body) {

        Cargo cargo = findByNome(body.nome_cargo());

        Funcionario existingFuncionario = verificaFuncionario(id);

        if (funcionarioRepository.existsByRg(body.rg()) && !existingFuncionario.getRg().equals(body.rg())) {
            throw new RuntimeException("Já existe um funcionário com o RG fornecido.");
        }

        existingFuncionario.setNome(body.nome());
        existingFuncionario.setRg(body.rg());
        existingFuncionario.setSalario(body.salario());
        existingFuncionario.setCargo(cargo);
        return funcionarioRepository.saveAndFlush(existingFuncionario);
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

    private Cargo findByNome(String nome){
        return cargoRepository.findByNome(nome).orElseThrow(()-> new RuntimeException("nome do cargo não encontrado"));
    }
}
