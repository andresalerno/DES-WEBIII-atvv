package com.comunicacao.api.mappers;

import org.springframework.stereotype.Component;

import com.comunicacao.api.dtos.ClienteDTO;
import com.comunicacao.api.modelos.Cliente;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        // Mapear os relacionamentos ou IDs
        return dto;
    }

    public Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        // Mapear os relacionamentos com base nos IDs, se necessário
        return cliente;
    }
}

