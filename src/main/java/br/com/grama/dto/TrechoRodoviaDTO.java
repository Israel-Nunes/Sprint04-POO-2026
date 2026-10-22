
package br.com.grama.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record TrechoRodoviaDTO(
        Integer id,

        @NotNull(message = "O quilômetro é obrigatório")
        @PositiveOrZero(message = "O quilômetro não pode ser negativo")
        Integer km,

        @NotNull(message = "A altura da vegetação é obrigatória")
        @DecimalMin(value = "0.0", message = "A altura da vegetação não pode ser negativa")
        BigDecimal alturaVegetacao,

        boolean areaUmida
) {
}
