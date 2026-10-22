
package br.com.grama.controller;

import br.com.grama.dto.TrechoRodoviaDTO;
import br.com.grama.model.TrechoRodovia;
import br.com.grama.service.TrechoRodoviaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/trechos")
public class TrechoRodoviaController {

    private final TrechoRodoviaService service;

    public TrechoRodoviaController(TrechoRodoviaService service) {
        this.service = service;
    }

    @GetMapping
    public List<TrechoRodoviaDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrechoRodoviaDTO> buscarPorId(
            @PathVariable Integer id) {
        return ResponseEntity.ok(
                paraDTO(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<TrechoRodoviaDTO> criar(
            @Valid @RequestBody TrechoRodoviaDTO dto) {
        TrechoRodovia criado = service.criar(paraEntidade(dto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paraDTO(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrechoRodoviaDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TrechoRodoviaDTO dto) {
        TrechoRodovia atualizado =
                service.atualizar(id, paraEntidade(dto));

        return ResponseEntity.ok(paraDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/altura-minima")
    public List<TrechoRodoviaDTO> buscarPorAltura(
            @RequestParam BigDecimal minimo) {
        return service.buscarPorAltura(minimo)
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    private TrechoRodoviaDTO paraDTO(TrechoRodovia trecho) {
        return new TrechoRodoviaDTO(
                trecho.getId(),
                trecho.getKm(),
                trecho.getAlturaVegetacao(),
                trecho.isAreaUmida()
        );
    }

    private TrechoRodovia paraEntidade(TrechoRodoviaDTO dto) {
        TrechoRodovia trecho = new TrechoRodovia();
        trecho.setKm(dto.km());
        trecho.setAlturaVegetacao(dto.alturaVegetacao());
        trecho.setAreaUmida(dto.areaUmida());
        return trecho;
    }
}
