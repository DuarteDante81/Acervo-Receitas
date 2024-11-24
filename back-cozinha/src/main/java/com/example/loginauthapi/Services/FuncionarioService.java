package com.example.loginauthapi.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.FuncionarioResponseDTO;
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

    public List<FuncionarioResponseDTO> list() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        // Mapeia a lista de funcionários para a lista de DTOs
        return funcionarios.stream()
                .map(funcionario -> new FuncionarioResponseDTO(
                        funcionario.getId_funcionario(),
                        funcionario.getNome(),
                        funcionario.getRg(),
                        funcionario.getSalario(),
                        funcionario.getData_adm(),
                        funcionario.getData_egresso(),
                        funcionario.getNome_fantasia(),
                        funcionario.getCargo() != null ? funcionario.getCargo().getNome() : null // Pegando o nome do cargo
                ))
                .collect(Collectors.toList());
    }

    private Funcionario criacaoFuncionario(Funcionario funcionario, RegisterRequestDTO body) {
        Optional<User> userOpt = userRepository.findByEmail(body.email());
        User user = userOpt.orElseGet(() -> {
            User newUser = new User();
            newUser.setSenha(passwordEncoder.encode(body.senha()));
            newUser.setEmail(body.email());
            newUser.setNome(body.nome());
            userRepository.save(newUser);
            return newUser;
        });
    
        Cargo cargo = findByNome(body.nome_cargo());
        if (cargo == null) {
            throw new IllegalArgumentException("Cargo não encontrado");
        }
    
        funcionario.setRg(body.rg());
        funcionario.setSalario(body.salario());
        funcionario.setCargo(cargo);
        funcionario.setNome(body.nome());
        funcionario.setData_ade(new Date());
        funcionario.setUser(user);
    
        return funcionarioRepository.save(funcionario);
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
