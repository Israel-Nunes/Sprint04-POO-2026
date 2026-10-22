
package br.com.grama;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ValidacaoApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornar400QuandoQuilometroForNegativo() throws Exception {
        String json = """
                {
                    "km": -5,
                    "alturaVegetacao": 10,
                    "areaUmida": false
                }
                """;

        mockMvc.perform(post("/api/trechos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400QuandoNomeDaEquipeEstiverVazio() throws Exception {
        String json = """
                {
                    "nome": "",
                    "tipo": "PODA"
                }
                """;

        mockMvc.perform(post("/api/equipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400QuandoIntervencaoForInvalida() throws Exception {
        String json = """
                {
                    "tipo": "",
                    "custoEstimado": -100,
                    "idEquipe": null
                }
                """;

        mockMvc.perform(post("/api/intervencoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
