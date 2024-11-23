package com.example.loginauthapi.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.model.Avaliacao;
import com.example.loginauthapi.model.Receitas;
import com.example.loginauthapi.repositories.AvaliacaoRepository;
import com.example.loginauthapi.repositories.ReceitasRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private ReceitasRepository receitasRepository;

    public Avaliacao create(Avaliacao avaliacao){
        Avaliacao AvaliacaoSalva = avaliacaoRepository.save(avaliacao);

        mediaNotas(avaliacao.getReceita());

        return AvaliacaoSalva;

    }

    private void mediaNotas(Receitas receita) {
        Optional<Double> mediaNotas = receitasRepository.mediaNotas(receita.getId_receita());
        receita.setMediaNota(0.0);
        if (mediaNotas.isPresent()) {
            receita.setMediaNota(0.0);
            // Caso haja avaliações, atualize a média
            receita.setMediaNota(mediaNotas.get());
        } else {
            // Se não houver avaliações, a média pode ser nula ou 0
            receita.setMediaNota(0.0);
        }

        // Salvar a receita com a média atualizada
        receitasRepository.save(receita);
    }
}