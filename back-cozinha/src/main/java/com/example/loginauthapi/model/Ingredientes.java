package com.example.loginauthapi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingrediente")
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingrediente;
    private String nome;
    private String descricao;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Receitas> receita;

    public Long getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(Long id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Receitas> getReceita() {
        return receita;
    }

    public void setReceita(List<Receitas> receita) {
        this.receita = receita;
    }

    
    
}
