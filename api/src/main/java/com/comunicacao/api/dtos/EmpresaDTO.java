package com.comunicacao.api.dtos;

import java.util.List;

import lombok.Data;
@Data
public class EmpresaDTO {
    
    private Long id;
    private String nome;
    private String cnpj;
    private String enderecoPrincipal;
    private List<Long> clientes;  // Armazenamos apenas os IDs dos clientes para evitar sobrecarga de dados
    private List<Long> servicosMercadorias;  // Armazenamos apenas os IDs dos serviços mercadoria
    private List<Long> veiculos;  // Armazenamos apenas os IDs dos veículos

    
}
