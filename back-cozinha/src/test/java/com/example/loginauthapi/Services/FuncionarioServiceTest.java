package com.example.loginauthapi.Services;

import com.example.loginauthapi.model.Funcionario;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FuncionarioServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Teste que regasta todos os funcionários do banco de dados")
    void testListarFuncionarioNoBanco() {

        // Enviar a requisição GET para o endpoint /funcionario
        ResponseEntity<List<Funcionario>> response = restTemplate.exchange(
                "/funcionario/",
                HttpMethod.GET,
                null,  // Não é necessário passar credenciais porque está liberado no security
                new ParameterizedTypeReference<List<Funcionario>>() {}
        );

        // Verificar se a resposta foi bem-sucedida (status 200)
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Resposta 200 OK - Sucesso ao obter os funcionários.");
        } else {
            throw new AssertionError("Resposta não bem-sucedida: " + response.getStatusCode());
        }

        // Verificar se a lista de funcionários não está vazia
        List<Funcionario> funcionarios = response.getBody();
        assertFalse(funcionarios.isEmpty(), "Deve haver pelo menos um funcionário no banco.");

        // Exibir no console os dados dos funcionários para visualização
        funcionarios.forEach(funcionario -> System.out.println("Funcionario: " + funcionario.getNome()));
    }
}