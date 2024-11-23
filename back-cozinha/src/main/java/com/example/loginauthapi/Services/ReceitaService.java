package com.example.loginauthapi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.model.Categoria;
import com.example.loginauthapi.model.Receitas;
import com.example.loginauthapi.repositories.CategoriaRepository;
import com.example.loginauthapi.repositories.ReceitasRepository;

@Service
public class ReceitaService {

    @Autowired
    private ReceitasRepository receitasRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Receitas create(Receitas receitas){
        Receitas result = receitasRepository.saveAndFlush(receitas);
        return result;
    }

    public List<Receitas> List(){
        return receitasRepository.findAll();
    }

    public Receitas update(Receitas receitas){
        Receitas result = receitasRepository.saveAndFlush(receitas);
        return result;
    } 

    public void delete(Long id){
        Receitas receitas = verificaReceitas(id);
        receitasRepository.delete(receitas); 
    }

    private Receitas verificaReceitas(Long id){
        return receitasRepository.findById(id).orElseThrow(()-> new RuntimeException("Receita não encontrado"));
    }
    public Categoria findByNome(String nome_categoria){
        return categoriaRepository.findByDescricao(nome_categoria).orElseThrow(()-> 
                                             new RuntimeException("Nome da categoria não encontrado"));
    }

}
