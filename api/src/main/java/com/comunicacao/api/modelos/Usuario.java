package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Geração automática do ID (se for um banco relacional)
    private Long id;

    private String nome;
    private String email;
    
}
