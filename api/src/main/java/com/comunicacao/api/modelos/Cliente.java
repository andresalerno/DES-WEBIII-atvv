package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

import javax.persistence.Column;

@Entity
@Data
public class Cliente {

    @Id
    private Long id;

    private String nome;

    @Column(length = 20)
    private String documentos; // Ex: CPF, RG

    @Column(length = 15)
    private String telefones; // Pode ser um único telefone ou um JSON com múltiplos telefones

    private String endereco;

    // Getters and Setters
}
