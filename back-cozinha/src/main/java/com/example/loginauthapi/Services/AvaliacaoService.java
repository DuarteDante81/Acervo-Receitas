package com.example.loginauthapi.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.AvaliacaoResponseDTO;
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

    public List<AvaliacaoResponseDTO> List(){
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        return avaliacoes.stream()
                    .map(avaliacao -> new AvaliacaoResponseDTO(
                        avaliacao.getId_degustacao(),
                        avaliacao.getDescricao(),
                        avaliacao.getNota(),
                        avaliacao.getData_degustacao(),
                        avaliacao.getReceita()!= null ? avaliacao.getReceita().getNome(): null,
                        avaliacao.getDegustador() != null ? avaliacao.getDegustador().getNome(): null
                    ))
                    .collect(Collectors.toList());
    }

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
            receita.setMediaNota(mediaNotas.get());
        } else {
            receita.setMediaNota(0.0);
        }

        receitasRepository.save(receita);
    }
}
