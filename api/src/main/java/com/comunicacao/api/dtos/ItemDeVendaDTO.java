package com.comunicacao.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;  // Adicionando a anotação Lombok

@Data
@NoArgsConstructor  // Gera o construtor sem parâmetros
public class ItemDeVendaDTO {

    private Long id;
    private Long servicoMercadoriaId;  // ID do ServicoMercadoria
    private Long vendaId;  // ID da Venda
    private Integer quantidade;
    private Double valorTotal;

    // Construtor com parâmetros necessários (opcional, se você precisar)
    public ItemDeVendaDTO(Long id, Long servicoMercadoriaId, Long vendaId, Integer quantidade, Double valorTotal) {
        this.id = id;
        this.servicoMercadoriaId = servicoMercadoriaId;
        this.vendaId = vendaId;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }
}
