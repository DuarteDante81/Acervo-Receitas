package com.example.loginauthapi.dto;

import java.time.LocalDate;

public record ReceitasRequestDTO( Long id_receita, String nome, LocalDate data_inclusao, 
String descricao, String modo_preparo, Double num_porcao, Double nota, String ind_inedita, Long cozinheiro) {

}