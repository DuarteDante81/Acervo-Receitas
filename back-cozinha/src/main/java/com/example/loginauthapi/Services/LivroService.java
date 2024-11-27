package com.example.loginauthapi.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.IngredienteDTO;
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
        

    // Mapeamento das receitas associadas ao livro
    List<Livros> livros = livrosRepository.findAll(); // Busca todos os livros

    // Para cada livro encontrado, mapeie as receitas associadas
    return livros.stream()
            .map(livro -> {
                List<Receitas> receitas = livro.getReceitas();
                 // Recupera as receitas associadas ao livro
                List<ReceitasResponseDTO> receitasDTO = receitas.stream()
                        .map(receita -> {
                            List<IngredientesResponseDTO> ingredientesDTO = receita.getIngredientes().stream()
                                    .map(ingrediente -> new IngredientesResponseDTO(
                                            ingrediente.getNome(),
                                            ingrediente.getDescricao()
                                    ))
                                    .collect(Collectors.toList());
        
                            String nomeCategoria = receita.getCategoria() != null ? receita.getCategoria().getDescricao() : null;
        
                            return new ReceitasResponseDTO(
                                receita.getId_receita(),
                                receita.getNome(),
                                receita.getData_inclusao(),
                                receita.getDescricao(),
                                receita.getModo_preparo(),
                                receita.getNum_porcao(),
                                receita.getInd_inedita(),
                                ingredientesDTO,  
                                nomeCategoria
                            );
                        })
                        .collect(Collectors.toList());

                // Retorna o DTO do livro, incluindo as receitas associadas
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
    


