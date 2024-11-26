package com.example.loginauthapi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.model.Cargo;
import com.example.loginauthapi.repositories.CargoRepository;

@Service
public class CargoService {
    
    @Autowired
    private CargoRepository cargoRepository;

    public Cargo create(Cargo cargo){
        Cargo result = cargoRepository.saveAndFlush(cargo);
        return result;
    }

    public List<Cargo> List(){
        return cargoRepository.findAll();
    }

    public void delete(Long id){
        Cargo cargo = verificaCargo(id);
        cargoRepository.delete(cargo);
    }
    
    public Cargo verificaCargo(Long id){
        return cargoRepository.findById(id).orElseThrow(()-> new RuntimeException("Cargo não encontrado"));
    }
    
}
