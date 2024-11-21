package com.example.loginauthapi.repositories;



import java.util.Optional;

import com.example.loginauthapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

    Optional<Funcionario> findByNome(String nome);
    boolean existsByRg(String rg);
    boolean existsByNome(String nome);

    Optional<Funcionario> findByUser(User user);

}
