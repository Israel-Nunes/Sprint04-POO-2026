
package br.com.grama.controller;

import br.com.grama.dto.EquipeManutencaoDTO;
import br.com.grama.model.EquipeManutencao;
import br.com.grama.service.EquipeManutencaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeManutencaoController {

    private final EquipeManutencaoService service;

    public EquipeManutencaoController(EquipeManutencaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EquipeManutencaoDTO> listarTodas() {
        return service.listarTodas()
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeManutencaoDTO> buscarPorId(
            @PathVariable Integer id) {
        return ResponseEntity.ok(
                paraDTO(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<EquipeManutencaoDTO> criar(
            @Valid @RequestBody EquipeManutencaoDTO dto) {
        EquipeManutencao criada = service.criar(paraEntidade(dto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paraDTO(criada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeManutencaoDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EquipeManutencaoDTO dto) {
        EquipeManutencao atualizada =
                service.atualizar(id, paraEntidade(dto));

        return ResponseEntity.ok(paraDTO(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo")
    public List<EquipeManutencaoDTO> buscarPorTipo(
            @RequestParam String tipo) {
        return service.buscarPorTipo(tipo)
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    private EquipeManutencaoDTO paraDTO(EquipeManutencao equipe) {
        return new EquipeManutencaoDTO(
                equipe.getId(),
                equipe.getNome(),
                equipe.getTipo()
        );
    }

    private EquipeManutencao paraEntidade(EquipeManutencaoDTO dto) {
        EquipeManutencao equipe = new EquipeManutencao();
        equipe.setNome(dto.nome());
        equipe.setTipo(dto.tipo());
        return equipe;
    }
}
