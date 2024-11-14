package com.example.loginauthapi.dto;

import java.time.LocalDate;

public record FuncionarioResponseDTO(String nome,String rg,
Double salario, LocalDate data_adm, LocalDate data_egresso,Long cargo, String nome_fantasia) {

    public FuncionarioResponseDTO(String nome,String rg,
    Double salario, LocalDate data_adm, LocalDate data_egresso,Long cargo,String nome_fantasia){
        
        this.nome = nome;
        this.rg = rg;
        this.salario = salario;
        this.data_adm = data_adm;
        this.data_egresso = data_egresso;
        this.cargo = cargo;
        this.nome_fantasia = nome_fantasia;
    }
}
