package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class ItemDeVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServicoMercadoria servicoMercadoria;

    @ManyToOne
    private Venda venda;

    private Integer quantidade;

    private Double valorTotal;

    // Getters and Setters
}
