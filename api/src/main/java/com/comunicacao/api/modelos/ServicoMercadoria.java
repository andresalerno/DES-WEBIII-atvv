package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.comunicacao.api.enumeracoes.TipoCompra;

import lombok.Data;

import java.util.Date;

@Entity
@Data
public class ServicoMercadoria {

    @Id
    private Long id;

    private String nome;

    private String descricao;

    private Double valor;

    private Date dataCadastro;
    
    @Enumerated(EnumType.STRING)
    private TipoCompra tipo;

    // Getters and Setters
}
