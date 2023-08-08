package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import br.com.meta.apivotoscooperativa.repository.PautaRepository;
import br.com.meta.apivotoscooperativa.repository.SessaoVotacaoRepository;
import br.com.meta.apivotoscooperativa.service.PautaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoVotacaoController {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaRepository pautaRepository;
    private final PautaService pautaService;

    public SessaoVotacaoController(SessaoVotacaoRepository sessaoVotacaoRepository, PautaRepository pautaRepository, PautaService pautaService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaRepository = pautaRepository;
        this.pautaService = pautaService;
    }

    @PostMapping("/{idPauta}")
    public ResponseEntity<?> abreSessaoVotacao(@PathVariable Long idPauta, @RequestParam(required = false) Integer duracaoMinutos) {
        Optional<Pauta> pautaOptional = pautaRepository.findById(idPauta);

        if (pautaOptional.isEmpty()) {
            return new ResponseEntity<>("Pauta não encontrada.", HttpStatus.NOT_FOUND);
        }

        Pauta pauta = pautaOptional.get();

        // Verifica se a pauta já tem uma sessão de votação em andamento
        Optional<SessaoVotacao> sessaoVotacaoOptional = sessaoVotacaoRepository.findById(pauta.getId());
        if (sessaoVotacaoOptional.isPresent() && sessaoVotacaoOptional.get().isEmAndamento()) {
            return new ResponseEntity<>("Uma sessão de votação para esta pauta já está em andamento.", HttpStatus.BAD_REQUEST);
        }

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setDataHoraInicio(LocalDateTime.now());
        sessaoVotacao.setDataHoraFim(LocalDateTime.now().plusMinutes(duracaoMinutos != null ? duracaoMinutos : 1));

        SessaoVotacao savedSessaoVotacao = sessaoVotacaoRepository.save(sessaoVotacao);

        return new ResponseEntity<>(savedSessaoVotacao, HttpStatus.CREATED);
    }
}
