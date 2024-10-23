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
    private Long id;
    private LocalDate dataAvalia;

    @Column(nullable = false)
    private Double nota;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario degustador;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receitas receita;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAvalia() {
		return dataAvalia;
	}

	public void setDataAvalia(LocalDate dataAvalia) {
		this.dataAvalia = dataAvalia;
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


	public Receitas getReceita() {
		return receita;
	}

	public void setReceita(Receitas receita) {
		this.receita = receita;
	}

	public Funcionario getDegustador() {
		return degustador;
	}

	public void setDegustador(Funcionario degustador) {
		this.degustador = degustador;
	}
	
    
}
