package com.comunicacao.api.dtos;

import java.util.List;

import lombok.Data;
@Data
public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private List<Long> documentosIds;  // Apenas IDs ou simplificação
    private List<Long> telefonesIds;   // Apenas IDs ou simplificação
    private List<Long> enderecosIds;   // Apenas IDs ou simplificação

    // Construtores, Getters e Setters
}
