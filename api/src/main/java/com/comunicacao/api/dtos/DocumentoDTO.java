package com.comunicacao.api.dtos;

import com.comunicacao.api.enumeracoes.TipoDocumento;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Gera o construtor sem argumentos
public class DocumentoDTO {

    private Long id;
    private TipoDocumento tipo;
    private String numero;
    private Long clienteId;
    private Long funcionarioId;

    // Construtor com parâmetros
    public DocumentoDTO(Long id, TipoDocumento tipo, String numero) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
    }
}
