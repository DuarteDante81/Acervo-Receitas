package com.example.loginauthapi.Services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.IngredienteDTO;
import com.example.loginauthapi.dto.IngredientesResponseDTO;
import com.example.loginauthapi.dto.ReceitasRequestDTO;
import com.example.loginauthapi.dto.ReceitasResponseDTO;
import com.example.loginauthapi.model.Categoria;
import com.example.loginauthapi.model.Ingredientes;
import com.example.loginauthapi.model.Receitas;
import com.example.loginauthapi.repositories.CategoriaRepository;
import com.example.loginauthapi.repositories.IngredientesRepository;
import com.example.loginauthapi.repositories.ReceitasRepository;

@Service
public class ReceitaService {

    @Autowired
    private ReceitasRepository receitasRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private IngredientesRepository ingredientesRepository;
  
   public List<ReceitasResponseDTO> List() {
        List<Receitas> receitas = receitasRepository.findAll();

        return receitas.stream()
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
    }

    public Receitas update(ReceitasRequestDTO body,Long id){
    Categoria categoria = findByNome(body.nome_categoria());
    
    Receitas receitas = verificaReceitas(id);
    
    receitas.setNome(body.nome());
    receitas.setData_inclusao(new Date());
    receitas.setDescricao(body.descricao());
    receitas.setModo_preparo(body.modo_preparo());
    receitas.setNum_porcao(body.num_porcao());
    receitas.setInd_inedita(true);
    receitas.setCategoria(categoria);

    List<Ingredientes> ingredientesExistentes = receitas.getIngredientes(); 
    
        if (body.ingredientes() != null && !body.ingredientes().isEmpty()) {
            
            Set<Ingredientes> ingredientesParaSalvar = new HashSet<>();

            for (IngredienteDTO ingredienteDTO : body.ingredientes()) {
                Ingredientes ingredienteExistente = ingredientesExistentes.stream()
                    .filter(i -> i.getNome().equals(ingredienteDTO.nome()))  
                    .findFirst()
                    .orElse(null);

                if (ingredienteExistente != null) {
                    ingredienteExistente.setDescricao(ingredienteDTO.descricao());
                    ingredientesParaSalvar.add(ingredienteExistente);
                } else {
                    Ingredientes novoIngrediente = new Ingredientes();
                    novoIngrediente.setNome(ingredienteDTO.nome());
                    novoIngrediente.setDescricao(ingredienteDTO.descricao());
                    novoIngrediente.setReceita(receitas);
                    ingredientesParaSalvar.add(novoIngrediente);
                }
            }

            ingredientesRepository.saveAll(ingredientesParaSalvar);
            
            for (Ingredientes ingredienteExistente : ingredientesExistentes) {
                if (ingredientesParaSalvar.stream().noneMatch(i -> i.getNome().equals(ingredienteExistente.getNome()))) {
                    ingredientesRepository.delete(ingredienteExistente); 
                }
            }
        }
    return receitasRepository.save(receitas);
    } 

    private Receitas verificaReceitas(Long id){
        return receitasRepository.findById(id).orElseThrow(()-> new RuntimeException("Receita não encontrado"));
    }
    public Categoria findByNome(String nome_categoria){
        return categoriaRepository.findByDescricao(nome_categoria).orElseThrow(()-> 
                                             new RuntimeException("Nome da categoria não encontrado"));
    }
    public List<Receitas> findByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("A lista de IDs não pode ser vazia.");
        }

        List<Receitas> receitas = receitasRepository.findAllById(ids);

        if (receitas.size() != ids.size()) {
            List<Long> foundIds = receitas.stream().map(Receitas::getId_receita).collect(Collectors.toList());
            ids.removeAll(foundIds); 
            throw new RuntimeException("Não foram encontradas as receitas para os IDs: " + ids);
        }

        return receitas;
    }

}
