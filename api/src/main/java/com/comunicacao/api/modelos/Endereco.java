package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.comunicacao.api.enumeracoes.TipoEndereco;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipo;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "cliente_id")  // Referência ao Cliente
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")  // Referência ao Funcionario
    private Funcionario funcionario;
}
