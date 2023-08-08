package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.dto.VotoDTO;
import br.com.meta.apivotoscooperativa.dto.ResultadoVotacaoDTO;
import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.model.Voto;
import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.service.VotoService;
import br.com.meta.apivotoscooperativa.repository.PautaRepository;
import br.com.meta.apivotoscooperativa.repository.AssociadoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    private final VotoService votoService;
    private final PautaRepository pautaRepository;
    private final AssociadoRepository associadoRepository;

    public VotoController(VotoService votoService, PautaRepository pautaRepository, AssociadoRepository associadoRepository) {
        this.votoService = votoService;
        this.pautaRepository = pautaRepository;
        this.associadoRepository = associadoRepository;
    }

    @PostMapping("/{idPauta}")
    public ResponseEntity<?> votar(@PathVariable Long idPauta, @RequestBody VotoDTO votoDto) {
        if (votoDto.getAssociadoId() == null) {
            return new ResponseEntity<>("O id do associado não pode ser vazio.", HttpStatus.BAD_REQUEST);
        }
        Optional<Pauta> pautaOptional = pautaRepository.findById(idPauta);
        Optional<Associado> associadoOptional = associadoRepository.findById(votoDto.getAssociadoId());

        if (pautaOptional.isEmpty()) {
            return new ResponseEntity<>("Pauta não encontrada.", HttpStatus.NOT_FOUND);
        }

        if (associadoOptional.isEmpty()) {
            return new ResponseEntity<>("Associado não encontrado.", HttpStatus.NOT_FOUND);
        }

        Voto voto = new Voto();
        voto.setPauta(pautaOptional.get());
        voto.setAssociado(associadoOptional.get());
        voto.setVoto(votoDto.getVoto());

        try {
            Voto savedVoto = votoService.createVoto(voto);
            return new ResponseEntity<>(savedVoto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Um erro ocorreu ao tentar registrar o voto. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pautas/{id}/resultado")
    public ResponseEntity<ResultadoVotacaoDTO> getResultadoVotacao(@PathVariable Long id) {
        try {
            ResultadoVotacaoDTO resultado = votoService.obterResultadoVotacao(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>("Verifique se o associadoId possui um Id válido e se voto é true ou false.", HttpStatus.BAD_REQUEST);
    }
}
