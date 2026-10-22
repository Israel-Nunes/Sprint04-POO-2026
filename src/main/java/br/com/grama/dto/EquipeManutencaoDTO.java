
package br.com.grama.dto;

import jakarta.validation.constraints.NotBlank;

public record EquipeManutencaoDTO(
        Integer id,

        @NotBlank(message = "O nome da equipe é obrigatório")
        String nome,

        @NotBlank(message = "O tipo da equipe é obrigatório")
        String tipo
) {
}
