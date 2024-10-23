package com.example.loginauthapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
