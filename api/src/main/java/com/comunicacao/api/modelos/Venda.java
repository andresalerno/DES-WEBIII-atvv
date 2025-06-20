package com.comunicacao.api.modelos;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServicoMercadoria servicoMercadoria;

    @ManyToOne
    private Cliente cliente;

    private Double valor;

    private Date dataVenda; // Data da venda


}
