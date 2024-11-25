package com.example.loginauthapi.dto;

import java.util.Date;

public record AvaliacaoResponseDTO(Long id,String descricao, Double nota, Date data_criacao,String nome_receita,String nome_degustador) {

}
