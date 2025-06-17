package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

import java.util.List;

import javax.persistence.Column;

@Entity
@Data
public class Funcionario {

    @Id
    private Long id;
    
    @ManyToOne
    private Empresa empresa;

    private String nome;

    @OneToMany
    private List<Documento> documento;

    @OneToMany
    private List<Telefone> telefone;

    @OneToMany
    private List<Endereco> enderecos;

    private String perfil; // Ex: Gerente, Vendedor, etc.


    // Getters and Setters
}
