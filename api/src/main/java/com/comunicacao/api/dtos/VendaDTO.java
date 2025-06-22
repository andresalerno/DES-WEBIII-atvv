package com.comunicacao.api.dtos;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {

    @Schema(example = "1", description = "ID da venda")
    private Long id;

    @Schema(example = "5", description = "ID do serviço ou mercadoria vendido (opcional se for usado item)")
    private Long servicoMercadoriaId;

    @Schema(example = "3", description = "ID do cliente que realizou a compra")
    private Long clienteId;

    @Schema(example = "250.00", description = "Valor total da venda")
    private Double valor;

    @Schema(example = "2024-06-01", description = "Data da venda")
    private Date dataVenda;

    @Schema(description = "Itens que compõem a venda")
    private List<ItemDeVendaDTO> itens; // ✅ agora a venda tem itens
    
    @Schema(example = "1", description = "ID da empresa vinculada ao serviço")
    private Long empresaId;

}
