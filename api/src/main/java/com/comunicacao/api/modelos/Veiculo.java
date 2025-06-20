package com.comunicacao.api.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private String marca;

    private String placa;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id") // Nome da coluna de chave estrangeira
    private Empresa empresa;  // Relacionamento com a empresa (loja)

}
