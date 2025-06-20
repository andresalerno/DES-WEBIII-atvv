package com.comunicacao.api.dtos;

import com.comunicacao.api.enumeracoes.TipoTelefone;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Gera o construtor sem argumentos
public class TelefoneDTO {

    private Long id;
    private TipoTelefone tipo;
    private String numero;
    private Long clienteId;  // ID do Cliente (para simplificar o relacionamento)
    private Long funcionarioId; // ID do Funcionario (se necessário)

    // Construtor com Long e String
    public TelefoneDTO(Long id, String numero) {
        this.id = id;
        this.numero = numero;
    }
}
