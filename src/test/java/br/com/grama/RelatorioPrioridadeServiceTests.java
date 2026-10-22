
package br.com.grama;

import br.com.grama.model.RelatorioPrioridade;
import br.com.grama.model.TrechoRodovia;
import br.com.grama.repository.RelatorioPrioridadeRepository;
import br.com.grama.repository.TrechoRodoviaRepository;
import br.com.grama.service.RelatorioPrioridadeService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RelatorioPrioridadeServiceTests {

    @Test
    void deveClassificarTrechosNosQuatroNiveisDePrioridade() {
        TrechoRodoviaRepository trechoRepository =
                mock(TrechoRodoviaRepository.class);

        RelatorioPrioridadeRepository relatorioRepository =
                mock(RelatorioPrioridadeRepository.class);

        when(trechoRepository.findAll()).thenReturn(List.of(
                criarTrecho("80", false),  // Crescimento 88: urgente
                criarTrecho("50", false),  // Crescimento 55: crítico
                criarTrecho("25", false),  // Crescimento 27,5: atenção
                criarTrecho("20", false)   // Crescimento 22: normal
        ));

        when(relatorioRepository.save(any(RelatorioPrioridade.class)))
                .thenAnswer(invocacao -> invocacao.getArgument(0));

        RelatorioPrioridadeService service =
                new RelatorioPrioridadeService(
                        relatorioRepository,
                        trechoRepository);

        RelatorioPrioridade relatorio = service.gerarRelatorio();

        assertEquals(1, relatorio.getQuantidadeAlta());
        assertEquals(1, relatorio.getQuantidadeMedia());
        assertEquals(1, relatorio.getQuantidadeMonitoramento());
        assertEquals(1, relatorio.getQuantidadeNormal());

        assertEquals(
                "Total de trechos: 4. Urgente: 1. Crítico: 1. Atenção: 1. Normal: 1.",
                relatorio.getResumo());
    }

    private TrechoRodovia criarTrecho(String altura, boolean areaUmida) {
        TrechoRodovia trecho = new TrechoRodovia();
        trecho.setAlturaVegetacao(new BigDecimal(altura));
        trecho.setAreaUmida(areaUmida);
        return trecho;
    }
}
