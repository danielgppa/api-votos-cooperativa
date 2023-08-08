package br.com.meta.apivotoscooperativa.repository;

import br.com.meta.apivotoscooperativa.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    Optional<Associado> findByCpf(String cpf);
}
