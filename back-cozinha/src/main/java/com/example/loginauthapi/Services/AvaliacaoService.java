package com.example.loginauthapi.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.dto.AvaliacaoRequestDTO;
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
    public Avaliacao update(Long id,AvaliacaoRequestDTO body){
        Avaliacao avaliacao =  avaliacaoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Degustação não encontrada"));
        Avaliacao AvaliacaoSalva = avaliacaoRepository.save(avaliacao);
        avaliacao.setNota(body.nota());
        avaliacao.setDescricao(body.descricao());
        avaliacao.setData_degustacao(new Date());
        mediaNotas(avaliacao.getReceita());

        return AvaliacaoSalva;

    }

    private void mediaNotas(Receitas receita) {
        Optional<Double> mediaNotas = receitasRepository.mediaNotas(receita.getId_receita());
            if (mediaNotas.isPresent()) {
                receita.setMediaNota(mediaNotas.get()); 
            } else {
                receita.setMediaNota(0.0); 
            }
        receitasRepository.save(receita);
        receitasRepository.flush();
    }

    public void delete(Long id){
        Avaliacao avaliacao =  avaliacaoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Degustação não encontrada"));
         avaliacaoRepository.delete(avaliacao);
    }
}
