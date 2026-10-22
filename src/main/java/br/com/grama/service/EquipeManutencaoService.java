
package br.com.grama.service;

import br.com.grama.model.EquipeManutencao;
import br.com.grama.repository.EquipeManutencaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeManutencaoService {

    private final EquipeManutencaoRepository repository;

    public EquipeManutencaoService(
            EquipeManutencaoRepository repository) {
        this.repository = repository;
    }

    public List<EquipeManutencao> listarTodas() {
        return repository.findAll();
    }

    public EquipeManutencao buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Equipe não encontrada: " + id));
    }

    public EquipeManutencao criar(EquipeManutencao equipe) {
        validar(equipe);
        return repository.save(equipe);
    }

    public EquipeManutencao atualizar(
            Integer id, EquipeManutencao dados) {

        EquipeManutencao equipe = buscarPorId(id);
        validar(dados);

        equipe.setNome(dados.getNome());
        equipe.setTipo(dados.getTipo());

        return repository.save(equipe);
    }

    public void excluir(Integer id) {
        EquipeManutencao equipe = buscarPorId(id);
        repository.delete(equipe);
    }

    public List<EquipeManutencao> buscarPorTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    private void validar(EquipeManutencao equipe) {
        if (equipe == null) {
            throw new IllegalArgumentException(
                    "Equipe não pode ser nula");
        }

        if (equipe.getNome() == null
                || equipe.getNome().isBlank()) {
            throw new IllegalArgumentException(
                    "O nome da equipe é obrigatório");
        }

        if (equipe.getTipo() == null
                || equipe.getTipo().isBlank()) {
            throw new IllegalArgumentException(
                    "O tipo da equipe é obrigatório");
        }
    }
}
