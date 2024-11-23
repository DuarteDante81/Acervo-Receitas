package com.example.loginauthapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.loginauthapi.model.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas,Long>{

    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.receita.id_receita = :receitaId")
    Optional<Double> mediaNotas(Long receitaId);
}
