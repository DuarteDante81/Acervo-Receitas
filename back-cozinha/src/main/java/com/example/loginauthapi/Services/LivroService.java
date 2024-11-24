package com.example.loginauthapi.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.IngredientesResponseDTO;
import com.example.loginauthapi.dto.LivrosRequestDTO;
import com.example.loginauthapi.dto.LivrosResponseDTO;
import com.example.loginauthapi.dto.ReceitasResponseDTO;
import com.example.loginauthapi.model.Livros;
import com.example.loginauthapi.model.Receitas;
import com.example.loginauthapi.repositories.FuncionarioRepository;
import com.example.loginauthapi.repositories.LivrosRepository;
import com.example.loginauthapi.repositories.ReceitasRepository;

@Service
public class LivroService {

    @Autowired
    private LivrosRepository livrosRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ReceitasRepository receitasRepository;
    @Autowired
    private ReceitaService receitaService;

    public List<LivrosResponseDTO> list(){
        List<Livros> livros = livrosRepository.findAll();

        return livros.stream()
                .map(livro -> {
                    List<ReceitasResponseDTO> receitasDTO = receitaService.List();

                    return new LivrosResponseDTO(
                            livro.getId_livro(),
                            livro.getTitulo(),
                            livro.getCod_isbn(),
                            livro.getData_criacao(),
                            livro.getPublicado(),
                            receitasDTO
                    );
                })
                .collect(Collectors.toList());
    }
    

}
