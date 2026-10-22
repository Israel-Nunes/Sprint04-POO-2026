package br.com.grama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_TRECHO_RODOVIA")
public class TrechoRodovia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRECHO")
    private Integer id;

    @Column(name = "KM", nullable = false)
    private Integer km;

    @Column(name = "ALTURA_VEGETACAO", nullable = false, precision = 10, scale = 2)
    private BigDecimal alturaVegetacao;

    @Column(name = "AREA_UMIDA", nullable = false)
    private Integer areaUmidaBanco;

    public TrechoRodovia() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public BigDecimal getAlturaVegetacao() {
        return alturaVegetacao;
    }

    public void setAlturaVegetacao(BigDecimal alturaVegetacao) {
        this.alturaVegetacao = alturaVegetacao;
    }

    public boolean isAreaUmida() {
        return areaUmidaBanco != null && areaUmidaBanco == 1;
    }

    public void setAreaUmida(boolean areaUmida) {
        this.areaUmidaBanco = areaUmida ? 1 : 0;
    }

    @JsonIgnore
    public Integer getAreaUmidaBanco() {
        return areaUmidaBanco;
    }

    public void setAreaUmidaBanco(Integer areaUmidaBanco) {
        this.areaUmidaBanco = areaUmidaBanco;
    }

    public double calcularCrescimento() {
        if (alturaVegetacao == null) {
            return 0.0;
        }

        double altura = alturaVegetacao.doubleValue();

        return isAreaUmida() ? altura * 1.5 : altura * 1.1;
    }
}