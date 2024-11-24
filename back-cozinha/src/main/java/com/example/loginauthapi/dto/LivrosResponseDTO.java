package com.example.loginauthapi.dto;

import java.util.Date;
import java.util.List;

public record LivrosResponseDTO(
    Long id,
     String titulo,
     String cod_isbn,
     Date datda_criacao,
     String publicado,
     List<ReceitasResponseDTO> receitas
) {

}
