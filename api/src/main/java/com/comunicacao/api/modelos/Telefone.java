package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.comunicacao.api.enumeracoes.TipoTelefone;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    private String numero;

    @ManyToOne
    @JoinColumn(name = "cliente_id")  // Referência ao Cliente
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")  // Referência ao Funcionario
    private Funcionario funcionario;
}
