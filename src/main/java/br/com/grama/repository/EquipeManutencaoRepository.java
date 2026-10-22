package br.com.grama.repository;

import br.com.grama.model.EquipeManutencao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipeManutencaoRepository
        extends JpaRepository<EquipeManutencao, Integer> {

    List<EquipeManutencao> findByTipo(String tipo);
}