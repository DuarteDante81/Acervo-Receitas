package com.example.loginauthapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    Optional<Categoria> findByDescricao(String nome_categoria);
}
