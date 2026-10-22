
package br.com.grama.controller;

import br.com.grama.model.RelatorioPrioridade;
import br.com.grama.service.RelatorioPrioridadeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioPrioridadeController {

    private final RelatorioPrioridadeService service;

    public RelatorioPrioridadeController(
            RelatorioPrioridadeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RelatorioPrioridade> gerarRelatorio() {
        RelatorioPrioridade relatorio = service.gerarRelatorio();
        return ResponseEntity.status(HttpStatus.CREATED).body(relatorio);
    }

    @GetMapping
    public List<RelatorioPrioridade> listarHistorico() {
        return service.listarHistorico();
    }

    @GetMapping("/periodo")
    public List<RelatorioPrioridade> buscarPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime inicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fim) {

        return service.buscarPorPeriodo(inicio, fim);
    }
}
