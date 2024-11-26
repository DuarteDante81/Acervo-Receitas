package com.example.loginauthapi.model;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "livro")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_livro;
    @Column(nullable = false)
    private String titulo;
    @Column(unique = true)
    private String cod_isbn;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data_criacao;
    @Column(length = 3)
    private String publicado;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "funcionario_id")
    private Funcionario editor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "publicacoes", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn(name = "receita_id"))
    private List<Receitas> receitas;


    public Long getId_livro() {
        return id_livro;
    }

    public void setId_livro(Long id_livro) {
        this.id_livro = id_livro;
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

    public Funcionario getEditor() {
        return editor;
    }

    public void setEditor(Funcionario editor) {
        this.editor = editor;
    }

    public List<Receitas> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receitas> receitas) {
        this.receitas = receitas;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getPublicado() {
        return publicado;
    }

    public void setPublicado(String publicado) {
        this.publicado = publicado;
    } 
    
    
}
