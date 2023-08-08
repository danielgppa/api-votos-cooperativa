package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Optional;

@Service
public class AssociadoService {

    @Autowired
    public AssociadoRepository associadoRepository;

    public Associado createAssociado(Associado associado) throws Exception {
        if (!validaCPF(associado.getCpf())) {
            throw new Exception("CPF inválido ou inexistente.");
        }

        Optional<Associado> associadoExistente = associadoRepository.findByCpf(associado.getCpf());

        if (associadoExistente.isPresent()) {
            throw new Exception("Este CPF já está registrado.");
        }

        return associadoRepository.save(associado);
    }



    private boolean validaCPF(String cpf) {
        final String uri = "https://api.nfse.io/validate/NaturalPeople/taxNumber/" + cpf;

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> result = restTemplate.getForEntity(uri, Map.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                Map<String, Boolean> body = result.getBody();
                return body != null && Boolean.TRUE.equals(body.get("valid"));
            }
            return false;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            }
            throw e;
        }
    }
}
