package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Empresa {

    @Id
    private Long id;

    private String nome;

    private String cnpj;

    private String enderecoPrincipal;

    // Relacionamento com funcionários, clientes, etc.

    // Getters and Setters
}

