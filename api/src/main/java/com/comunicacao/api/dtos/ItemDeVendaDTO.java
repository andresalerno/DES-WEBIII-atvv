package com.comunicacao.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDeVendaDTO {

    @Schema(example = "1", description = "ID do item de venda")
    private Long id;

    @Schema(example = "10", description = "ID do serviço ou mercadoria vendido")
    private Long servicoMercadoriaId;

    @Schema(example = "5", description = "ID da venda associada")
    private Long vendaId;

    @Schema(example = "2", description = "Quantidade do item vendido")
    private Integer quantidade;

    @Schema(example = "199.90", description = "Valor total do item vendido (unitário × quantidade)")
    private Double valorTotal;

    // Construtor adicional com argumentos
    public ItemDeVendaDTO(Long id, Long servicoMercadoriaId, Long vendaId, Integer quantidade, Double valorTotal) {
        this.id = id;
        this.servicoMercadoriaId = servicoMercadoriaId;
        this.vendaId = vendaId;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }
}
