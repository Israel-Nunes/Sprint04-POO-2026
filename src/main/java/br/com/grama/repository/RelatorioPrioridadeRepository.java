package br.com.grama.repository;

import br.com.grama.model.RelatorioPrioridade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RelatorioPrioridadeRepository
        extends JpaRepository<RelatorioPrioridade, Integer> {

    List<RelatorioPrioridade> findByDataGeracaoBetween(
            LocalDateTime inicio, LocalDateTime fim);

    List<RelatorioPrioridade> findAllByOrderByDataGeracaoDesc();
}