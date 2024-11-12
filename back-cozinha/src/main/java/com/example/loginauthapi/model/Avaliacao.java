package com.example.loginauthapi.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "degustacao")
public class Avaliacao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_degustacao;
    private LocalDate data_degustacao;

    @Column(nullable = false)
    private Double nota;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "funcionario")
    private Funcionario degustador;

    @ManyToOne
    @JoinColumn(name = "receita")
    private Receitas receita;

	public Long getId_degustacao() {
		return id_degustacao;
	}

	public void setId_degustacao(Long id_degustacao) {
		this.id_degustacao = id_degustacao;
	}

	public LocalDate getData_degustacao() {
		return data_degustacao;
	}

	public void setData_degustacao(LocalDate data_degustacao) {
		this.data_degustacao = data_degustacao;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getDegustador() {
		return degustador;
	}

	public void setDegustador(Funcionario degustador) {
		this.degustador = degustador;
	}

	public Receitas getReceita() {
		return receita;
	}

	public void setReceita(Receitas receita) {
		this.receita = receita;
	}

	
	
    
}
