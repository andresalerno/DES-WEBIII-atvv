package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.comunicacao.api.enumeracoes.TipoTelefone;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Telefone {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    private String numero;

    // Getters and Setters
}
