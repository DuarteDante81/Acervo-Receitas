package com.example.loginauthapi.model;

import java.util.List;

import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcargo;
	private String nome;
	@Column(unique = true)
	private String rg;
	private Double salario;
	private Date data_admi;
	private Date data_egresso;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	@OneToMany(mappedBy = "cozinheiro")
	private List<Receitas> receitas;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "livro_id")
	private Livros livros;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meta_id")
	private Meta metas;

	public Long getId() {
		return idcargo;
	}

	public void setId(Long id) {
		this.idcargo = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getIdcargo() {
		return idcargo;
	}

	public void setIdcargo(Long idcargo) {
		this.idcargo = idcargo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Date getData_admi() {
		return data_admi;
	}

	public void setData_admi(Date data_admi) {
		this.data_admi = data_admi;
	}

	public List<Receitas> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receitas> receitas) {
		this.receitas = receitas;
	}

	public Date getData_egresso() {
		return data_egresso;
	}

	public void setData_egresso(Date data_egresso) {
		this.data_egresso = data_egresso;
	}

	public Livros getLivros() {
		return livros;
	}

	public void setLivros(Livros livros) {
		this.livros = livros;
	}
	
	
}
