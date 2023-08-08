package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PautaService {

    @Autowired
    public PautaRepository pautaRepository;

    public Pauta createPauta(Pauta pauta) throws Exception {
        if (pauta.getTitulo() == null || pauta.getTitulo().isEmpty()) {
            throw new Exception("O título não pode ser vazio.");
        }

        Pauta pautaExistente = pautaRepository.findByTituloAndDescricao(pauta.getTitulo(), pauta.getDescricao()).orElse(null);
        if (pautaExistente != null) {
            throw new Exception("Já existe uma pauta com este título e descrição.");
        }


        return pautaRepository.save(pauta);
    }


}