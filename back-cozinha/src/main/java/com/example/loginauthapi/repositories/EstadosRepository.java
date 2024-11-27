package com.example.loginauthapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.model.Estados;

public interface EstadosRepository extends JpaRepository<Estados,Long>{
    Optional<Estados> findByNome(String nome);
}
