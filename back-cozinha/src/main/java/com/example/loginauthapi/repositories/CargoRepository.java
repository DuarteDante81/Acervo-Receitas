package com.example.loginauthapi.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo,Long>{
  
    Optional<Cargo> findByNome(String nome_cargo);
}
