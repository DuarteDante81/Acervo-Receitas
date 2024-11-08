package com.example.loginauthapi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargo")
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionario;
	
	public long getId_cargo() {
		return id;
	}
	public void setId_cargo(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return nome;
	}
	public void setDescricao(String nome) {
		this.nome = nome;
	}
	public List<Funcionario> getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
}
