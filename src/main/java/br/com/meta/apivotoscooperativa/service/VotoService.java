package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.model.Voto;
import br.com.meta.apivotoscooperativa.dto.ResultadoVotacaoDTO;
import br.com.meta.apivotoscooperativa.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
public class VotoService {

    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public Voto createVoto(Voto voto) throws Exception {
        Optional<Voto> votoExistente = votoRepository.findByPautaAndAssociado(voto.getPauta(), voto.getAssociado());

        if (votoExistente.isPresent()) {
            throw new IllegalArgumentException("O associado já votou nesta pauta.");
        }

        if (voto.getVoto() != true && voto.getVoto() != false) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O voto deve ser 'true' ou 'false'"
            );
        }

        if (voto.getAssociado().getId() == null) {
            throw new Exception("Insira o ID de um associado.");
        }

        return votoRepository.save(voto);
    }

    public ResultadoVotacaoDTO obterResultadoVotacao(Long pautaId) {
        try {
            List<Object[]> votos = votoRepository.countVotesByPautaId(pautaId);
            if (votos.isEmpty()) {
                throw new Exception("Não existem votos para essa pauta.");
            }

            Map<Boolean, Long> votosMap = new HashMap<>();
            for(Object[] voto : votos) {
                votosMap.put((Boolean) voto[0], (Long) voto[1]);
            }
            ResultadoVotacaoDTO resultado = new ResultadoVotacaoDTO();
            resultado.setTotalSim(votosMap.getOrDefault(true, 0L));
            resultado.setTotalNao(votosMap.getOrDefault(false, 0L));
            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter o resultado da votação: " + e.getMessage(), e);
        }
    }

}
