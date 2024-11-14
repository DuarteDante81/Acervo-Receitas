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
    private Long id_metas;
    private Double mes_prod;
    private Double anos_prod;
    private Double quant_receitas;
    @OneToMany(mappedBy = "metas")
    private List<Funcionario> cozinheiro;
    
    public Long getId_metas() {
        return id_metas;
    }
    public void setId_metas(Long id_metas) {
        this.id_metas = id_metas;
    }
    public Double getMes_prod() {
        return mes_prod;
    }
    public void setMes_prod(Double mes_prod) {
        this.mes_prod = mes_prod;
    }
    public Double getQuant_receitas() {
        return quant_receitas;
    }
    public void setQuant_receitas(Double quant_receitas) {
        this.quant_receitas = quant_receitas;
    }
    public List<Funcionario> getCozinheiro() {
        return cozinheiro;
    }
    public void setCozinheiro(List<Funcionario> cozinheiro) {
        this.cozinheiro = cozinheiro;
    }
    public Double getAnos_prod() {
        return anos_prod;
    }
    public void setAnos_prod(Double anos_prod) {
        this.anos_prod = anos_prod;
    }
    
    

    
}
