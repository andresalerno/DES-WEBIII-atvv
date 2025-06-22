package com.comunicacao.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Gera construtor sem argumentos
@AllArgsConstructor // Gera construtor com todos os argumentos
public class VeiculoDTO {

    @Schema(example = "10", description = "ID do veículo")
    private Long id;

    @Schema(example = "Onix", description = "Modelo do veículo")
    private String modelo;

    @Schema(example = "Chevrolet", description = "Marca do veículo")
    private String marca;

    @Schema(example = "ABC-1234", description = "Placa do veículo")
    private String placa;

    @Schema(example = "1", description = "ID da empresa associada ao veículo")
    private Long empresaId;
}
