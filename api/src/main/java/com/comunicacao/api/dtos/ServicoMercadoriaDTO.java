package com.comunicacao.api.dtos;

import com.comunicacao.api.enumeracoes.TipoCompra;
import lombok.Data;
import java.util.Date;

@Data
public class ServicoMercadoriaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double valor;
    private Date dataCadastro;
    private TipoCompra tipo;
    private Long empresaId; // ID da empresa associada

    public ServicoMercadoriaDTO(Long id, String nome, String descricao, Double valor, Date dataCadastro, TipoCompra tipo, Long empresaId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.dataCadastro = dataCadastro;
        this.tipo = tipo;
        this.empresaId = empresaId;
    }
}
