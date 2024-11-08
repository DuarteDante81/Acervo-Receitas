package com.example.loginauthapi.dto;

import java.time.LocalDate;

public record RegisterRequestDTO(String nome, String email, String senha,String rg,Double salario,Long cargo, 
		LocalDate data_adm, LocalDate data_Egresso,String nome_fantasia){}
