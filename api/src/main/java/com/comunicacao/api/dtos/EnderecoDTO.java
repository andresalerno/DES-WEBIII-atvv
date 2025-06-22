package com.comunicacao.api.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.comunicacao.api.enumeracoes.TipoEndereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    @Schema(example = "10", description = "ID do endereço")
    private Long id;

    @NotNull
    @Schema(example = "COMERCIAL", description = "Tipo de endereço: RESIDENCIAL ou COMERCIAL")
    private TipoEndereco tipo;

    @NotBlank
    @Schema(example = "Rua das Flores", description = "Nome da rua ou logradouro")
    private String logradouro;

    @NotBlank
    @Schema(example = "123", description = "Número do imóvel")
    private String numero;

    @NotBlank
    @Schema(example = "Centro", description = "Bairro do endereço")
    private String bairro;

    @NotBlank
    @Schema(example = "São Paulo", description = "Cidade do endereço")
    private String cidade;

    @NotBlank
    @Schema(example = "SP", description = "Estado (UF)")
    private String estado;

    @NotBlank
    @Schema(example = "01234-567", description = "CEP no formato 00000-000")
    private String cep;

    @Schema(example = "1", description = "ID do cliente associado a este endereço (se aplicável)")
    private Long clienteId;
}
