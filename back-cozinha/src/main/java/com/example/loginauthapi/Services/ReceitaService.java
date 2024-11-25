package com.example.loginauthapi.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.IngredientesResponseDTO;
import com.example.loginauthapi.dto.ReceitasResponseDTO;
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

   public List<ReceitasResponseDTO> List() {
        List<Receitas> receitas = receitasRepository.findAll();

        return receitas.stream()
                .map(receita -> {
                    List<IngredientesResponseDTO> ingredientesDTO = receita.getIngredientes().stream()
                            .map(ingrediente -> new IngredientesResponseDTO(
                                    ingrediente.getId_ingrediente(),
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
    public List<Receitas> findByIds(List<Long> ids) {
        return receitasRepository.findAllById(ids);
    }

}
