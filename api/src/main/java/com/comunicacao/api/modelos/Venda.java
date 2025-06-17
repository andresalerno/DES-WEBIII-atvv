package com.comunicacao.api.modelos;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Venda {

    @Id
    private Long id;

    @ManyToOne
    private ServicoMercadoria servicoMercadoria;

    @ManyToOne
    private Cliente cliente;

    private Double valor;

    private Date dataVenda; // Data da venda


}
