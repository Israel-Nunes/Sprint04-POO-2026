package br.com.grama.repository;

import br.com.grama.model.TrechoRodovia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TrechoRodoviaRepository
        extends JpaRepository<TrechoRodovia, Integer> {

    List<TrechoRodovia> findByAlturaVegetacaoGreaterThanEqual(
            BigDecimal minimo);
}