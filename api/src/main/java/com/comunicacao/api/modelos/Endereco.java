package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.comunicacao.api.enumeracoes.TipoEndereco;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Endereco {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipo;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    // Getters and Setters
}
