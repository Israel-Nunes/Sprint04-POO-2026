
package br.com.grama;

import br.com.grama.model.TrechoRodovia;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrechoRodoviaTests {

    @Test
    void deveCalcularCrescimentoEmAreaUmida() {
        TrechoRodovia trecho = new TrechoRodovia();
        trecho.setAlturaVegetacao(new BigDecimal("60"));
        trecho.setAreaUmida(true);

        assertEquals(90.0, trecho.calcularCrescimento(), 0.001);
    }

    @Test
    void deveCalcularCrescimentoEmAreaSeca() {
        TrechoRodovia trecho = new TrechoRodovia();
        trecho.setAlturaVegetacao(new BigDecimal("40"));
        trecho.setAreaUmida(false);

        assertEquals(44.0, trecho.calcularCrescimento(), 0.001);
    }

    @Test
    void deveCalcularCrescimentoDeOitentaEmAreaSeca() {
        TrechoRodovia trecho = new TrechoRodovia();
        trecho.setAlturaVegetacao(new BigDecimal("80"));
        trecho.setAreaUmida(false);

        assertEquals(88.0, trecho.calcularCrescimento(), 0.001);
    }

    @Test
    void deveCalcularCrescimentoDeCinquentaEmAreaSeca() {
        TrechoRodovia trecho = new TrechoRodovia();
        trecho.setAlturaVegetacao(new BigDecimal("50"));
        trecho.setAreaUmida(false);

        assertEquals(55.0, trecho.calcularCrescimento(), 0.001);
    }

    @Test
    void deveCalcularCrescimentoDeVinteECincoEmAreaSeca() {
        TrechoRodovia trecho = new TrechoRodovia();
        trecho.setAlturaVegetacao(new BigDecimal("25"));
        trecho.setAreaUmida(false);

        assertEquals(27.5, trecho.calcularCrescimento(), 0.001);
    }

    @Test
    void deveCalcularCrescimentoAbaixoDeVinteECincoEmAreaSeca() {
        TrechoRodovia trecho = new TrechoRodovia();
        trecho.setAlturaVegetacao(new BigDecimal("20"));
        trecho.setAreaUmida(false);

        assertEquals(22.0, trecho.calcularCrescimento(), 0.001);
    }
}
