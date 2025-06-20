package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    private String perfil; // Ex: Gerente, Vendedor, etc.

   
 // Relacionamento com documentos, telefones, e endereços específicos de funcionário
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Documento> documentos;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefone> telefones;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Endereco> enderecos;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;


    // Getters and Setters
}
