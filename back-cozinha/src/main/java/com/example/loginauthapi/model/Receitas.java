package com.example.loginauthapi.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name= "receita")
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_receita;

    
    @Column(nullable = false, unique = true)  
    private String nome;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false) 
    private Date data_inclusao;

    @Column(nullable = false,length = 600)  
    private String descricao;

    @Column(nullable = false, length = 600)  
    private String modo_preparo;

    @Column(nullable = false)
    private Double num_porcao;

    @Column(nullable = false) 
    private boolean ind_inedita;

    @Column(nullable = true)
    private Double mediaNota;

    @ManyToOne
    @JoinColumn(name = "funcionario_id",nullable = false)
    private Funcionario cozinheiro;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL)
    private List<Avaliacao> degustacao;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL)
    private List<Ingredientes> ingredientes;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL)
    private List<Midia> midia;

    @ManyToOne
    @JoinColumn(name = "categoria_id") 
    private Categoria categoria;

    @ManyToMany(mappedBy = "receitas", cascade = CascadeType.ALL)
    private List<Livros> livros;

    // Getters e Setters

    public Long getId_receita() {
        return id_receita;
    }

    public void setId_receita(Long id_receita) {
        this.id_receita = id_receita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_inclusao() {
        return data_inclusao;
    }

    public void setData_inclusao(Date data_inclusao) {
        this.data_inclusao = data_inclusao;
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

    public Double getNum_porcao() {
        return num_porcao;
    }

    public void setNum_porcao(Double num_porcao) {
        this.num_porcao = num_porcao;
    }

    public boolean getInd_inedita() {
        return ind_inedita;
    }

    public void setInd_inedita(boolean ind_inedita) {
        this.ind_inedita = ind_inedita;
    }

    public Funcionario getCozinheiro() {
        return cozinheiro;
    }

    public void setCozinheiro(Funcionario cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    public List<Avaliacao> getDegustacao() {
        return degustacao;
    }

    public void setDegustacao(List<Avaliacao> degustacao) {
        this.degustacao = degustacao;
    }

    public List<Ingredientes> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Midia> getMidia() {
        return midia;
    }

    public void setMidia(List<Midia> midia) {
        this.midia = midia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getMediaNota() {
        return mediaNota;
    }

    public void setMediaNota(Double mediaNota) {
        this.mediaNota = mediaNota;
    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    
}
