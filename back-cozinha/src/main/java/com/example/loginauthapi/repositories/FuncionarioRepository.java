package com.example.loginauthapi.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.model.User;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

    Optional<Funcionario> findByNome(String nome);
    Optional<Funcionario> findByUser(User user);
}
