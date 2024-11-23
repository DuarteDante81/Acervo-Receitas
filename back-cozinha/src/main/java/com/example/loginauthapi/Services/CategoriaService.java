package com.example.loginauthapi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.model.Categoria;
import com.example.loginauthapi.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria create(Categoria categoria){
        Categoria result = categoriaRepository.saveAndFlush(categoria);
        return result;
    }

    public List<Categoria> List(){
        return categoriaRepository.findAll();
    }

    public void delete(Long id){
        Categoria categoria = verificaCategoria(id);
        categoriaRepository.delete(categoria);
        
    }

    private Categoria verificaCategoria(Long id){
        return categoriaRepository.findById(id).orElseThrow(()-> new RuntimeException("Categoria não encontrada!!"));
    }
}
