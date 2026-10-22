
package br.com.grama.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_RELATORIO_PRIORIDADE")
public class RelatorioPrioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RELATORIO")
    private Integer id;

    @Column(name = "DATA_GERACAO")
    private LocalDateTime dataGeracao;

    @Column(name = "QT_ALTA")
    private Integer quantidadeAlta;

    @Column(name = "QT_MEDIA")
    private Integer quantidadeMedia;

    @Column(name = "QT_MONITORAMENTO")
    private Integer quantidadeMonitoramento;

    @Column(name = "QT_NORMAL")
    private Integer quantidadeNormal;

    @Column(name = "RESUMO", length = 1000)
    private String resumo;

    public RelatorioPrioridade() {
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Integer getQuantidadeAlta() {
        return quantidadeAlta;
    }

    public void setQuantidadeAlta(Integer quantidadeAlta) {
        this.quantidadeAlta = quantidadeAlta;
    }

    public Integer getQuantidadeMedia() {
        return quantidadeMedia;
    }

    public void setQuantidadeMedia(Integer quantidadeMedia) {
        this.quantidadeMedia = quantidadeMedia;
    }

    public Integer getQuantidadeMonitoramento() {
        return quantidadeMonitoramento;
    }

    public void setQuantidadeMonitoramento(Integer quantidadeMonitoramento) {
        this.quantidadeMonitoramento = quantidadeMonitoramento;
    }

    public Integer getQuantidadeNormal() {
        return quantidadeNormal;
    }

    public void setQuantidadeNormal(Integer quantidadeNormal) {
        this.quantidadeNormal = quantidadeNormal;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
}
