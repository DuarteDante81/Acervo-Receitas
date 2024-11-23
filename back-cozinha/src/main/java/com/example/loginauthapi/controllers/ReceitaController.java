package com.example.loginauthapi.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginauthapi.Services.ReceitaService;
import com.example.loginauthapi.Services.UserServices;
import com.example.loginauthapi.dto.IngredienteDTO;
import com.example.loginauthapi.dto.ReceitasRequestDTO;
import com.example.loginauthapi.dto.ReceitasResponseDTO;
import com.example.loginauthapi.model.Receitas;
import com.example.loginauthapi.model.Categoria;
import com.example.loginauthapi.model.Funcionario;
import com.example.loginauthapi.model.Ingredientes;
import com.example.loginauthapi.model.User;
import com.example.loginauthapi.repositories.FuncionarioRepository;
import com.example.loginauthapi.repositories.IngredientesRepository;
import com.example.loginauthapi.repositories.ReceitasRepository;
import com.example.loginauthapi.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServices userService; 

    @Autowired
    private IngredientesRepository ingredientesRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository; 

    
    @GetMapping("/")
    public ResponseEntity<List<ReceitasResponseDTO>> listAllReceitas() {
        List<ReceitasResponseDTO> receitas = receitaService.List();
        return ResponseEntity.ok(receitas);
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody @Valid ReceitasRequestDTO body, @RequestHeader("Authorization") String token) {
        String email = tokenService.validateToken(token.replace("Barear ", ""));

        if (email == null) {
            return ResponseEntity.status(401).body("Token inválido ou expirado");
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        Funcionario funcionario = funcionarioRepository.findByUser(user).orElseThrow(()-> new RuntimeException("Usuario não encontrado")); // Supondo que você tenha esse método no repositório Funcionario
        if (funcionario == null) {
            return ResponseEntity.status(404).body("Funcionário não encontrado");
        }

        Categoria categoria = receitaService.findByNome(body.nome_categoria());

        Receitas receitas = new Receitas();
        receitas.setNome(body.nome());
        receitas.setData_inclusao(new Date());
        receitas.setDescricao(body.descricao());
        receitas.setModo_preparo(body.modo_preparo());
        receitas.setNum_porcao(body.num_porcao());
        receitas.setInd_inedita(true);
        receitas.setCozinheiro(funcionario); 
        receitas.setCategoria(categoria);

        receitasRepository.save(receitas);

        if (body.ingredientes() != null && !body.ingredientes().isEmpty()) {
            for (IngredienteDTO ingredienteDTO : body.ingredientes()) {
                Ingredientes ingrediente = new Ingredientes();
                ingrediente.setNome(ingredienteDTO.nome()); 
                ingrediente.setDescricao(""); 
                ingrediente.setReceita(receitas);
                ingredientesRepository.save(ingrediente);
            }
            return ResponseEntity.status(200).body("receita criada");}
        
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public Receitas update(@RequestBody @Valid Receitas receitas){
        return receitaService.update(receitas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
         receitaService.delete(id);
         return ResponseEntity.ok().build();
    }


}
