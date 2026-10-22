
package br.com.grama.repository;

import br.com.grama.model.IntervencaoOperacional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface IntervencaoOperacionalRepository
        extends JpaRepository<IntervencaoOperacional, Integer> {

    List<IntervencaoOperacional> findByTipo(String tipo);

    List<IntervencaoOperacional> findByCustoEstimadoGreaterThanEqual(
            BigDecimal custoMinimo);

    List<IntervencaoOperacional> findByEquipe_Id(Integer idEquipe);
}
