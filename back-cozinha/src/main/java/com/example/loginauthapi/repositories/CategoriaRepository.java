package com.example.loginauthapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
