package com.example.loginauthapi.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.RegisterRequestDTO;
import com.example.loginauthapi.model.Cargo;
import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.model.User;
import com.example.loginauthapi.repositories.CargoRepository;
import com.example.loginauthapi.repositories.FuncionarioRepository;
import com.example.loginauthapi.repositories.UserRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private UserRepository userRepository;
     @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Funcionario create(Funcionario funcionario,RegisterRequestDTO body){
        funcionario = new Funcionario();
        return criacaoFuncionario(funcionario,body);
    }

    public List<Funcionario> list(){
        return funcionarioRepository.findAll();
    }

    private Funcionario criacaoFuncionario(Funcionario funcionario, RegisterRequestDTO body){
        Optional<User> user = userRepository.findByEmail(body.email());
        
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setSenha(passwordEncoder.encode(body.senha()));
            newUser.setEmail(body.email());
            newUser.setNome(body.nome());
            userRepository.save(newUser);
        
            Cargo cargo = findByNome(body.nome_cargo());
            funcionario.setRg(body.rg());
            funcionario.setSalario(body.salario());
            funcionario.setCargo(cargo);
            funcionario.setNome(body.nome());
            funcionario.setData_ade(new Date());
            funcionario.setUser(newUser);
            return funcionarioRepository.save(funcionario);
        }else{
            User result = userRepository.findByEmail(body.email()).orElseThrow();
            Cargo cargo = findByNome(body.nome_cargo());
            funcionario.setRg(body.rg());
            funcionario.setSalario(body.salario());
            funcionario.setCargo(cargo);
            funcionario.setNome(body.nome());
            funcionario.setData_ade(new Date());
            funcionario.setUser(result);
            return funcionarioRepository.save(funcionario);}
    }

    public Funcionario update(Long id, RegisterRequestDTO body){
            Cargo cargo = findByNome(body.nome_cargo());
            Funcionario funcionario = verificaFuncionario(id);
            funcionario.setRg(body.rg());
            funcionario.setSalario(body.salario());
            funcionario.setCargo(cargo);
            funcionario.setNome(body.nome());
            funcionario.setData_ade(new Date());
            return funcionarioRepository.save(funcionario);
    }
    
    public void delete(Long id){
        Funcionario funcionario = verificaFuncionario(id);
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
