package br.com.meta.apivotoscooperativa.repository;

import br.com.meta.apivotoscooperativa.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Optional<Pauta> findByTituloAndDescricao(String titulo, String descricao);

}
