package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.service.AssociadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associados")
public class AssociadoController {

    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping
    public ResponseEntity<?> createAssociado(@RequestBody Associado associado) {
        try {
            Associado savedAssociado = associadoService.createAssociado(associado);
            return new ResponseEntity<>(savedAssociado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
