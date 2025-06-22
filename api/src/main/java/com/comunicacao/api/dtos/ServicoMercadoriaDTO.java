package com.comunicacao.api.dtos;

import com.comunicacao.api.enumeracoes.TipoCompra;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoMercadoriaDTO {

    @Schema(example = "1", description = "ID do serviço ou mercadoria")
    private Long id;

    @Schema(example = "Troca de óleo", description = "Nome do serviço ou mercadoria")
    private String nome;

    @Schema(example = "Troca de óleo sintético para motor 1.6", description = "Descrição detalhada do serviço ou produto")
    private String descricao;

    @Schema(example = "149.90", description = "Valor do serviço ou produto")
    private Double valor;

    @Schema(example = "2024-01-01", description = "Data de cadastro do serviço ou mercadoria")
    private Date dataCadastro;

    @Schema(example = "SERVICO", description = "Tipo de compra: SERVICO ou MERCADORIA")
    private TipoCompra tipo;

    @Schema(example = "2", description = "ID da empresa associada")
    private Long empresaId;
}
