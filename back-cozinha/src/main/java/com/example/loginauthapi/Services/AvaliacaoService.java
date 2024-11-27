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
import com.example.loginauthapi.model.Funcionario;
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

    public void create(AvaliacaoRequestDTO body, Funcionario degustador, Receitas receita) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDegustador(degustador);
        avaliacao.setData_degustacao(new Date());
        avaliacao.setReceita(receita);
        avaliacao.setDescricao(body.descricao());
        avaliacao.setNota(body.nota());

        avaliacaoRepository.save(avaliacao);

        mediaNotas(receita);
    }

    public Avaliacao update(Long id, AvaliacaoRequestDTO body) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Degustação não encontrada"));

        avaliacao.setDescricao(body.descricao());
        avaliacao.setNota(body.nota());
        avaliacao.setData_degustacao(new Date());

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);

        if (avaliacao.getReceita() != null) {
            mediaNotas(avaliacao.getReceita());
        }

        return avaliacaoSalva;
    }

    private void mediaNotas(Receitas receita) {
        Optional<Double> mediaNotas = receitasRepository.mediaNotas(receita.getId_receita());
        if (mediaNotas.isPresent()) {
            receita.setMediaNota(mediaNotas.get());
        } else {
            receita.setMediaNota(0.0); 
        }
        receitasRepository.save(receita);
    }

    public void delete(Long id){
        Avaliacao avaliacao =  avaliacaoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Degustação não encontrada"));
         avaliacaoRepository.delete(avaliacao);
    }
}
