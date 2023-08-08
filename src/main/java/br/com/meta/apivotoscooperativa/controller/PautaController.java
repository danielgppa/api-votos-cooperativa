package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.repository.PautaRepository;
import br.com.meta.apivotoscooperativa.service.PautaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pautas")
public class PautaController {

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<?> createPauta(@Valid @RequestBody Pauta pauta) {
        try {
            Pauta savedPauta = pautaService.createPauta(pauta);
            return new ResponseEntity<>(savedPauta, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Um erro ocorreu ao tentar criar a pauta. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
