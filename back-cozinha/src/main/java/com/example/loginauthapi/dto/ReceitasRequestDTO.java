package com.example.loginauthapi.dto;

import java.time.LocalDate;
import java.util.List;

public record ReceitasRequestDTO( Long id_receita, String nome, LocalDate data_inclusao, 
String descricao, String modo_preparo, Double num_porcao, Double nota, boolean ind_inedita, Long cozinheiro,
String nome_categoria, List<IngredienteDTO> ingredientes) {

}
