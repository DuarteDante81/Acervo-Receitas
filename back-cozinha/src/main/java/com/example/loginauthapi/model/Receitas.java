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
    private Long id_receita;
    private String nome;
    private LocalDate data_inclusao;
    private String descricao;
    private String modo_preparo;
    private Double num_porcao;
    private Double nota;
    private String ind_inedita;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario cozinheiro;

    @OneToMany(mappedBy = "receita")
    private List<Avaliacao> degustador;

    @ManyToMany
    @JoinTable(name = "receitas_ingredientes", joinColumns = @JoinColumn(name = "receitas_id"),
    inverseJoinColumns = @JoinColumn(name = "ingredientes_id"))
    private List<Ingredientes> ingredientes;

    @OneToMany(mappedBy = "id_receita")
    private List<Midia> midia;
    
    @ManyToOne
    @JoinColumn(name = "receita")
    private Categoria categoria;
    
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

    public LocalDate getData_inclusao() {
        return data_inclusao;
    }

    public void setData_inclusao(LocalDate data_inclusao) {
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

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getInd_inedita() {
        return ind_inedita;
    }

    public void setInd_inedita(String ind_inedita) {
        this.ind_inedita = ind_inedita;
    }

    public Funcionario getCozinheiro() {
        return cozinheiro;
    }

    public void setCozinheiro(Funcionario cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    public List<Avaliacao> getDegustador() {
        return degustador;
    }

    public void setDegustador(List<Avaliacao> degustador) {
        this.degustador = degustador;
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

    

    
	
    
}
