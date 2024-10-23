package com.example.loginauthapi.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "receita")
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String descricao;
    private String modo_preparo;
    private Double numero_porcao;
    private Double nota;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario cozinheiro;

    @OneToMany(mappedBy = "receita")
    private List<Avaliacao> degustador;

    @ManyToMany
    @JoinTable(name = "receitas_ingredientes", joinColumns = @JoinColumn(name = "receitas_id"),
    inverseJoinColumns = @JoinColumn(name = "ingredientes_id"))
    private List<Ingredientes> ingredientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getModo_preparo() {
        return modo_preparo;
    }

    public void setModo_preparo(String modo_preparo) {
        this.modo_preparo = modo_preparo;
    }

    public Double getNumero_porcao() {
        return numero_porcao;
    }

    public void setNumero_porcao(Double numero_porcao) {
        this.numero_porcao = numero_porcao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Funcionario getCozinheiro() {
        return cozinheiro;
    }

    public void setCozinheiro(Funcionario cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    public List<Ingredientes> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
    }

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Avaliacao> getDegustador() {
		return degustador;
	}

	public void setDegustador(List<Avaliacao> degustador) {
		this.degustador = degustador;
	}
	
    
}
