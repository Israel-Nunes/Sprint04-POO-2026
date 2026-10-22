
package br.com.grama.service;

import br.com.grama.model.RelatorioPrioridade;
import br.com.grama.model.TrechoRodovia;
import br.com.grama.repository.RelatorioPrioridadeRepository;
import br.com.grama.repository.TrechoRodoviaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RelatorioPrioridadeService {

    private final RelatorioPrioridadeRepository relatorioRepository;
    private final TrechoRodoviaRepository trechoRepository;

    public RelatorioPrioridadeService(
            RelatorioPrioridadeRepository relatorioRepository,
            TrechoRodoviaRepository trechoRepository) {
        this.relatorioRepository = relatorioRepository;
        this.trechoRepository = trechoRepository;
    }

    @Transactional
    public RelatorioPrioridade gerarRelatorio() {
        List<TrechoRodovia> trechos = trechoRepository.findAll();

        int urgente = 0;
        int critico = 0;
        int atencao = 0;
        int normal = 0;

        for (TrechoRodovia trecho : trechos) {
            double crescimento = trecho.calcularCrescimento();

            if (crescimento >= 80) {
                urgente++;
            } else if (crescimento >= 50) {
                critico++;
            } else if (crescimento >= 25) {
                atencao++;
            } else {
                normal++;
            }
        }

        RelatorioPrioridade relatorio = new RelatorioPrioridade();

        relatorio.setDataGeracao(LocalDateTime.now());
        relatorio.setQuantidadeAlta(urgente);
        relatorio.setQuantidadeMedia(critico);
        relatorio.setQuantidadeMonitoramento(atencao);
        relatorio.setQuantidadeNormal(normal);

        relatorio.setResumo(
                "Total de trechos: " + trechos.size()
                + ". Urgente: " + urgente
                + ". Crítico: " + critico
                + ". Atenção: " + atencao
                + ". Normal: " + normal + "."
        );

        return relatorioRepository.save(relatorio);
    }

    public List<RelatorioPrioridade> listarHistorico() {
        return relatorioRepository.findAllByOrderByDataGeracaoDesc();
    }

    public List<RelatorioPrioridade> buscarPorPeriodo(
            LocalDateTime inicio, LocalDateTime fim) {

        if (inicio == null || fim == null) {
            throw new IllegalArgumentException(
                    "As datas de início e fim são obrigatórias.");
        }

        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException(
                    "A data de início não pode ser posterior à data de fim.");
        }

        return relatorioRepository.findByDataGeracaoBetween(inicio, fim);
    }
}
