package com.example.loginauthapi.dto;

import java.util.List;

public record LivrosRequestDTO(
     String titulo,
     String cod_isbn,
     List<Long> receitasIds
) {}
