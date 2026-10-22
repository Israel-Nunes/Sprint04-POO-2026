
package br.com.grama.service;

import br.com.grama.model.TrechoRodovia;
import br.com.grama.repository.TrechoRodoviaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TrechoRodoviaService {

    private final TrechoRodoviaRepository repository;

    public TrechoRodoviaService(TrechoRodoviaRepository repository) {
        this.repository = repository;
    }

    public List<TrechoRodovia> listarTodos() {
        return repository.findAll();
    }

    public TrechoRodovia buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Trecho não encontrado: " + id));
    }

    public TrechoRodovia criar(TrechoRodovia trecho) {
        validar(trecho);
        return repository.save(trecho);
    }

    public TrechoRodovia atualizar(Integer id, TrechoRodovia dados) {
        TrechoRodovia trecho = buscarPorId(id);

        validar(dados);

        trecho.setKm(dados.getKm());
        trecho.setAlturaVegetacao(dados.getAlturaVegetacao());
        trecho.setAreaUmida(dados.isAreaUmida());

        return repository.save(trecho);
    }

    public void excluir(Integer id) {
        TrechoRodovia trecho = buscarPorId(id);
        repository.delete(trecho);
    }

    public List<TrechoRodovia> buscarPorAltura(BigDecimal minimo) {
        if (minimo == null || minimo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "A altura mínima deve ser maior ou igual a zero");
        }

        return repository.findByAlturaVegetacaoGreaterThanEqual(minimo);
    }

    private void validar(TrechoRodovia trecho) {
        if (trecho == null) {
            throw new IllegalArgumentException("Trecho não pode ser nulo");
        }

        if (trecho.getKm() == null || trecho.getKm() < 0) {
            throw new IllegalArgumentException(
                    "KM deve ser maior ou igual a zero");
        }

        if (trecho.getAlturaVegetacao() == null
                || trecho.getAlturaVegetacao()
                        .compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Altura da vegetação deve ser maior ou igual a zero");
        }
    }
}
