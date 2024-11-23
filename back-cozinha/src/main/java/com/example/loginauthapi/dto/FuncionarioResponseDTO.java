package com.example.loginauthapi.dto;

import java.util.Date;

public record FuncionarioResponseDTO(
Long id_funcionario,
String nome,
String rg,
Double salario,
Date data_adm,
Date data_egresso,
String nome_fantasia,
String nome_cargo) {}
