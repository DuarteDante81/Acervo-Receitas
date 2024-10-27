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
	private long id_cargo;
	private String descricao;
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionario;
	
	public long getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(long id_cargo) {
		this.id_cargo = id_cargo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Funcionario> getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
}
