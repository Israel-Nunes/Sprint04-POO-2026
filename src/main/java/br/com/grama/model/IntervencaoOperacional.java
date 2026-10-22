
package br.com.grama.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_INTERVENCAO_OPERACIONAL")
public class IntervencaoOperacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INTERVENCAO")
    private Integer id;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "CUSTO_ESTIMADO", nullable = false, precision = 10, scale = 2)
    private BigDecimal custoEstimado;

    @ManyToOne
    @JoinColumn(name = "ID_EQUIPE")
    private EquipeManutencao equipe;

    public IntervencaoOperacional() {
    }

    public IntervencaoOperacional(
            String tipo,
            BigDecimal custoEstimado,
            EquipeManutencao equipe) {
        this.tipo = tipo;
        this.custoEstimado = custoEstimado;
        this.equipe = equipe;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(BigDecimal custoEstimado) {
        this.custoEstimado = custoEstimado;
    }

    public EquipeManutencao getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeManutencao equipe) {
        this.equipe = equipe;
    }
}
