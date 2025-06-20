package com.comunicacao.api.dtos;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;  // Importando a anotação Lombok

@Data
@NoArgsConstructor  // Gera o construtor sem argumentos
public class VendaDTO {

    private Long id;
    private Long servicoMercadoriaId; // ID do serviço/mercadoria associado
    private Long clienteId; // ID do cliente associado
    private Double valor;
    private Date dataVenda;

    // Construtor com os parâmetros necessários para o mapeamento
    public VendaDTO(Long id, Long servicoMercadoriaId, Long clienteId, Double valor, Date dataVenda) {
        this.id = id;
        this.servicoMercadoriaId = servicoMercadoriaId;
        this.clienteId = clienteId;
        this.valor = valor;
        this.dataVenda = dataVenda;
    }
}
