
package br.com.grama.service;

import br.com.grama.model.EquipeManutencao;
import br.com.grama.model.IntervencaoOperacional;
import br.com.grama.repository.EquipeManutencaoRepository;
import br.com.grama.repository.IntervencaoOperacionalRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IntervencaoOperacionalService {

    private final IntervencaoOperacionalRepository repository;
    private final EquipeManutencaoRepository equipeRepository;

    public IntervencaoOperacionalService(
            IntervencaoOperacionalRepository repository,
            EquipeManutencaoRepository equipeRepository) {
        this.repository = repository;
        this.equipeRepository = equipeRepository;
    }

    public List<IntervencaoOperacional> listarTodas() {
        return repository.findAll();
    }

    public IntervencaoOperacional buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Intervenção não encontrada"));
    }

    public IntervencaoOperacional criar(
            IntervencaoOperacional intervencao) {

        validarIntervencao(intervencao);
        validarEquipe(intervencao.getEquipe());

        return repository.save(intervencao);
    }

    public IntervencaoOperacional atualizar(
            Integer id, IntervencaoOperacional dados) {

        IntervencaoOperacional intervencao = buscarPorId(id);

        validarIntervencao(dados);
        validarEquipe(dados.getEquipe());

        intervencao.setTipo(dados.getTipo());
        intervencao.setCustoEstimado(dados.getCustoEstimado());
        intervencao.setEquipe(dados.getEquipe());

        return repository.save(intervencao);
    }

    public void excluir(Integer id) {
        IntervencaoOperacional intervencao = buscarPorId(id);
        repository.delete(intervencao);
    }

    public List<IntervencaoOperacional> buscarPorTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public List<IntervencaoOperacional> buscarPorCustoMinimo(
            BigDecimal custoMinimo) {

        if (custoMinimo == null
                || custoMinimo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O custo mínimo não pode ser negativo");
        }

        return repository.findByCustoEstimadoGreaterThanEqual(custoMinimo);
    }

    public List<IntervencaoOperacional> buscarPorEquipe(Integer idEquipe) {
        if (idEquipe == null) {
            throw new IllegalArgumentException(
                    "O ID da equipe é obrigatório");
        }

        return repository.findByEquipe_Id(idEquipe);
    }

    private void validarIntervencao(
            IntervencaoOperacional intervencao) {

        if (intervencao == null) {
            throw new IllegalArgumentException(
                    "A intervenção é obrigatória");
        }

        if (intervencao.getTipo() == null
                || intervencao.getTipo().isBlank()) {
            throw new IllegalArgumentException(
                    "O tipo da intervenção é obrigatório");
        }

        if (intervencao.getCustoEstimado() == null
                || intervencao.getCustoEstimado()
                        .compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O custo estimado deve ser maior ou igual a zero");
        }
    }

    private void validarEquipe(EquipeManutencao equipe) {
        if (equipe == null) {
            return;
        }

        Integer idEquipe = equipe.getId();

        if (idEquipe == null
                || !equipeRepository.existsById(idEquipe)) {
            throw new IllegalArgumentException(
                    "A equipe informada não existe");
        }
    }
}
