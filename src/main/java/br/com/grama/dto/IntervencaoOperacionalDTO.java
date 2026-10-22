
package br.com.grama.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record IntervencaoOperacionalDTO(
        Integer id,

        @NotBlank(message = "O tipo da intervenção é obrigatório")
        String tipo,

        @NotNull(message = "O custo estimado é obrigatório")
        @DecimalMin(value = "0.0", message = "O custo estimado não pode ser negativo")
        BigDecimal custoEstimado,

        @Positive(message = "O ID da equipe deve ser positivo")
        Integer idEquipe
) {
}
