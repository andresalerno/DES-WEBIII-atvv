package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Veiculo {

    @Id
    private Long id;

    private String modelo;

    private String marca;

    private String placa;


}
