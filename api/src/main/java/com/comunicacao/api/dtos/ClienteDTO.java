package com.comunicacao.api.dtos;

import com.comunicacao.api.dtos.DocumentoDTO;
import com.comunicacao.api.dtos.TelefoneDTO;
import com.comunicacao.api.dtos.EnderecoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Maria da Silva")
    private String nome;

    @Schema(example = "maria@email.com")
    private String email;

    @Schema(description = "Lista de documentos do cliente")
    private List<DocumentoDTO> documentos;

    @Schema(description = "Lista de telefones do cliente")
    private List<TelefoneDTO> telefones;

    @Schema(description = "Lista de endereços do cliente")
    private List<EnderecoDTO> enderecos;

    @Schema(description = "ID da empresa à qual o cliente pertence", example = "1")
    private Long empresaId;
}
