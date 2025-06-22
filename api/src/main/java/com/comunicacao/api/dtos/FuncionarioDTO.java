package com.comunicacao.api.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

    @Schema(example = "1", description = "ID do funcionário")
    private Long id;

    @Schema(example = "João Silva", description = "Nome completo do funcionário")
    private String nome;

    @Schema(example = "ADMIN", description = "Perfil do funcionário (ADMIN, VENDEDOR, etc.)")
    private String perfil;

    @Schema(description = "Lista de documentos vinculados ao funcionário")
    private List<DocumentoDTO> documentos;

    @Schema(description = "Lista de telefones vinculados ao funcionário")
    private List<TelefoneDTO> telefones;

    @Schema(description = "Lista de endereços vinculados ao funcionário")
    private List<EnderecoDTO> enderecos;

    @Schema(example = "1", description = "ID da empresa onde o funcionário trabalha")
    private Long empresaId;
}
