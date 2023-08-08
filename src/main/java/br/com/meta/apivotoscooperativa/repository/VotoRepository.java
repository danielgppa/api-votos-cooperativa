package br.com.meta.apivotoscooperativa.repository;

import br.com.meta.apivotoscooperativa.model.Voto;
import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Optional<Voto> findByPautaAndAssociado(Pauta pauta, Associado associado);

    @Query("SELECT v.voto, COUNT(v.voto) FROM Voto v WHERE v.pauta.id = :id GROUP BY v.voto")
    List<Object[]> countVotesByPautaId(@Param("id") Long pautaId);
}
