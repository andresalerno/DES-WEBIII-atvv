package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.comunicacao.api.enumeracoes.TipoCompra;

import lombok.Data;

import java.util.Date;

@Entity
@Data
public class ServicoMercadoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Double valor;

    private Date dataCadastro;
    
    @Enumerated(EnumType.STRING)
    private TipoCompra tipo;

    @ManyToOne
    @JoinColumn(name = "empresa_id") // O nome da coluna que será criada na tabela de serviços/mercadorias
    private Empresa empresa;
}
