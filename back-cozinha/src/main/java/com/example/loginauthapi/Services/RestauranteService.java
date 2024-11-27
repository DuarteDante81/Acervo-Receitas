package com.example.loginauthapi.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.RestauranteRequestDTO;
import com.example.loginauthapi.dto.RestauranteResponseDTO;
import com.example.loginauthapi.model.Estados;
import com.example.loginauthapi.model.Restaurante;
import com.example.loginauthapi.repositories.EstadosRepository;
import com.example.loginauthapi.repositories.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private EstadosRepository estadoRepository;  

    public List<RestauranteResponseDTO> List(){
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        return restaurantes.stream()
                .map(restaurante -> new RestauranteResponseDTO(
                    restaurante.getId_restaurante(),
                    restaurante.getNome(),
                    restaurante.getCnpj(),
                    restaurante.getEndereco(),
                    restaurante.getEstados()!= null ? restaurante.getEstados().getNome() : null 
                )).collect(Collectors.toList());
    }

    public Restaurante create(RestauranteRequestDTO body) {
        Estados estado = estadoRepository.findByNome(body.estado())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(body.nome());
        restaurante.setCnpj(body.cnpj());
        restaurante.setEndereco(body.endereco());
        restaurante.setEstados(estado);  

        return restauranteRepository.save(restaurante);
    }
    public Restaurante update(RestauranteRequestDTO body,Long id) {
        Estados estado = estadoRepository.findByNome(body.estado())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));

        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Restaurante não encontrado!"));
        
        restaurante.setNome(body.nome());
        restaurante.setCnpj(body.cnpj());
        restaurante.setEndereco(body.endereco());
        restaurante.setEstados(estado);  

        return restauranteRepository.save(restaurante);
    }
    public void delete(Long id){
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Restaurante não encontrado!")); 
        restauranteRepository.delete(restaurante);
    }
}
