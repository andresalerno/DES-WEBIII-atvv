package com.comunicacao.api.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaDTO {
    
    @Schema(description = "ID da empresa")
    private Long id;

    @Schema(example = "Autobots Ltda", description = "Nome da empresa")
    private String nome;

    @Schema(example = "12.345.678/0001-90", description = "CNPJ da empresa")
    private String cnpj;

    @Schema(example = "Av. Principal, 1000", description = "Endereço principal da empresa")
    private String enderecoPrincipal;

    @Schema(description = "Lista de IDs dos clientes da empresa")
    private List<Long> clientes;

    @Schema(description = "Lista de IDs dos serviços/mercadorias oferecidos")
    private List<Long> servicosMercadorias;

    @Schema(description = "Lista de IDs dos veículos pertencentes à empresa")
    private List<Long> veiculos;

    @Schema(description = "Lista de IDs dos funcionários da empresa")
    private List<FuncionarioDTO> funcionarios;
}
