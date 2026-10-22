
package br.com.grama.controller;

import br.com.grama.dto.IntervencaoOperacionalDTO;
import br.com.grama.model.EquipeManutencao;
import br.com.grama.model.IntervencaoOperacional;
import br.com.grama.service.EquipeManutencaoService;
import br.com.grama.service.IntervencaoOperacionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/intervencoes")
public class IntervencaoOperacionalController {

    private final IntervencaoOperacionalService service;
    private final EquipeManutencaoService equipeService;

    public IntervencaoOperacionalController(
            IntervencaoOperacionalService service,
            EquipeManutencaoService equipeService) {
        this.service = service;
        this.equipeService = equipeService;
    }

    @GetMapping
    public List<IntervencaoOperacionalDTO> listarTodas() {
        return service.listarTodas()
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntervencaoOperacionalDTO> buscarPorId(
            @PathVariable Integer id) {
        return ResponseEntity.ok(
                paraDTO(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<IntervencaoOperacionalDTO> criar(
            @Valid @RequestBody IntervencaoOperacionalDTO dto) {
        IntervencaoOperacional criada =
                service.criar(paraEntidade(dto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paraDTO(criada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IntervencaoOperacionalDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody IntervencaoOperacionalDTO dto) {
        IntervencaoOperacional atualizada =
                service.atualizar(id, paraEntidade(dto));

        return ResponseEntity.ok(paraDTO(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo")
    public List<IntervencaoOperacionalDTO> buscarPorTipo(
            @RequestParam String tipo) {
        return service.buscarPorTipo(tipo)
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    @GetMapping("/custo-minimo")
    public List<IntervencaoOperacionalDTO> buscarPorCustoMinimo(
            @RequestParam BigDecimal valor) {
        return service.buscarPorCustoMinimo(valor)
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    @GetMapping("/equipe")
    public List<IntervencaoOperacionalDTO> buscarPorEquipe(
            @RequestParam Integer idEquipe) {
        return service.buscarPorEquipe(idEquipe)
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    private IntervencaoOperacionalDTO paraDTO(
            IntervencaoOperacional intervencao) {
        Integer idEquipe = intervencao.getEquipe() == null
                ? null
                : intervencao.getEquipe().getId();

        return new IntervencaoOperacionalDTO(
                intervencao.getId(),
                intervencao.getTipo(),
                intervencao.getCustoEstimado(),
                idEquipe
        );
    }

    private IntervencaoOperacional paraEntidade(
            IntervencaoOperacionalDTO dto) {
        EquipeManutencao equipe = dto.idEquipe() == null
                ? null
                : equipeService.buscarPorId(dto.idEquipe());

        return new IntervencaoOperacional(
                dto.tipo(),
                dto.custoEstimado(),
                equipe
        );
    }
}
