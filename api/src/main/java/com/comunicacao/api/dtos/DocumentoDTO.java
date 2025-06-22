package com.comunicacao.api.dtos;

import com.comunicacao.api.enumeracoes.TipoDocumento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoDTO {

    @Schema(example = "1", description = "ID do documento")
    private Long id;

    @Schema(example = "CPF", description = "Tipo do documento: CPF, RG, CNH, etc.")
    private TipoDocumento tipo;

    @Schema(example = "123.456.789-00", description = "Número do documento")
    private String numero;

    @Schema(example = "2", description = "ID do cliente (se aplicável)")
    private Long clienteId;

    @Schema(example = "3", description = "ID do funcionário (se aplicável)")
    private Long funcionarioId;

    // Construtor específico para uso com 3 argumentos, se necessário
    public DocumentoDTO(Long id, TipoDocumento tipo, String numero) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
    }
}
