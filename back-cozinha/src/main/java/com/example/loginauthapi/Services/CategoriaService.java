package com.example.loginauthapi.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.CategoriaResponseDTO;
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

    public List<CategoriaResponseDTO> List(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> new CategoriaResponseDTO(
                    categoria.getDescricao()
                )).collect(Collectors.toList());   
    }

    public void delete(Long id){
        Categoria categoria = verificaCategoria(id);
        categoriaRepository.delete(categoria); 
    }

    private Categoria verificaCategoria(Long id){
        return categoriaRepository.findById(id).orElseThrow(()-> new RuntimeException("Categoria não encontrada!!"));
    }
}
