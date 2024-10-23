package com.example.loginauthapi.model;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idusuario;
	private String nome;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String senha;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Funcionario> funcionario;
	
	
	public Long getIdUsuario() {
		return idusuario;
	}
	public void setIdUsuario(Long id) {
		this.idusuario = id;
	}
	public String getName() {
		return nome;
	}
	public void setName(String name) {
		this.nome = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return senha;
	}
	public void setPassword(String password) {
		this.senha = password;
	}
	public List<Funcionario> getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
	
}
