package com.comunicacao.api.dtos;

import com.comunicacao.api.enumeracoes.TipoTelefone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTO {

    @Schema(example = "10", description = "ID do telefone")
    private Long id;

    @NotNull
    @Schema(example = "RESIDENCIAL", description = "Tipo de telefone: RESIDENCIAL, COMERCIAL ou CELULAR")
    private TipoTelefone tipo;

    @NotBlank
    @Schema(example = "(11) 98765-4321", description = "Número do telefone")
    private String numero;

    @Schema(example = "1", description = "ID do cliente associado, se aplicável")
    private Long clienteId;

    @Schema(example = "2", description = "ID do funcionário associado, se aplicável")
    private Long funcionarioId;
}
