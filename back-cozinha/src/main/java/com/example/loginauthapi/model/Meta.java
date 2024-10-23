package com.example.loginauthapi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name =  "metas")
public class Meta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantMin;
    private Double quantidade;
    @OneToMany(mappedBy = "metas")
    private List<Funcionario> cozinheiro;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getQuantMin() {
        return quantMin;
    }
    public void setQuantMin(Double quantMin) {
        this.quantMin = quantMin;
    }
    public Double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    public List<Funcionario> getCozinheiro() {
        return cozinheiro;
    }
    public void setCozinheiro(List<Funcionario> cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    
}
