package com.example.loginauthapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.model.Livros;

public interface LivrosRepository extends JpaRepository<Livros,Long>{

}
