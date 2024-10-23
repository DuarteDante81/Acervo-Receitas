package com.example.loginauthapi.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(unique = true)
    private String cod_isbn;

    @OneToMany(mappedBy = "livros")
    private List<Funcionario> editor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCod_isbn() {
        return cod_isbn;
    }

    public void setCod_isbn(String cod_isbn) {
        this.cod_isbn = cod_isbn;
    }

    public List<Funcionario> getEditor() {
        return editor;
    }

    public void setEditor(List<Funcionario> editor) {
        this.editor = editor;
    }

    
}
