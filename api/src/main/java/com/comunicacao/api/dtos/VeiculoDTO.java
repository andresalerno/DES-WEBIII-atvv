package com.comunicacao.api.dtos;

import lombok.Data;

@Data
public class VeiculoDTO {

    private Long id;
    private String modelo;
    private String marca;
    private String placa;
    private Long empresaId;  // ID da empresa associada (não a entidade completa)

    // Construtor sem argumentos para Lombok gerar automaticamente
    public VeiculoDTO() {}

    // Construtor com parâmetros para facilitar a criação de objetos
    public VeiculoDTO(Long id, String modelo, String marca, String placa, Long empresaId) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.empresaId = empresaId;
    }
}
