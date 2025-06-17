package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.comunicacao.api.enumeracoes.TipoDocumento;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Documento {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipo;

    private String numero;

    // Getters and Setters
}
