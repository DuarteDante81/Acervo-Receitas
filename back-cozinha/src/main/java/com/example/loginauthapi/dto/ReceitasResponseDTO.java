package com.example.loginauthapi.dto;

import java.util.Date;
import java.util.List;


public record ReceitasResponseDTO(Long id_receita,
String nome,
Date data_inclusao,
String descricao,
String modo_preparo,
Double num_porcao,
boolean ind_inedita,
List<IngredientesResponseDTO> ingredientes,
String nomeCategoria) {


    

    
}
