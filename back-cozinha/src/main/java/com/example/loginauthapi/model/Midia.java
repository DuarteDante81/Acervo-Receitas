package com.example.loginauthapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "midia")
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_midia;
    private String tipo;
    private String descricao;
    @Lob
    @Column(name = "midia", columnDefinition = "LONGBLOB")
    private byte[] midia;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receitas receita;

    public Long getId_midia() {
        return id_midia;
    }

    public void setId_midia(Long id_midia) {
        this.id_midia = id_midia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getMidia() {
        return midia;
    }

    public void setMidia(byte[] midia) {
        this.midia = midia;
    }

    public Receitas getreceita() {
        return receita;
    }

    public void setId_receita(Receitas receita) {
        this.receita = receita;
    }

    
}
