package com.example.loginauthapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingrediente")
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingrediente;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receitas receita;

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

    public Receitas getReceita() {
        return receita;
    }

    public void setReceita(Receitas receita) {
        this.receita = receita;
    }

    
    
}
