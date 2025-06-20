package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.EmpresaDTO;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Empresa;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {

    public EmpresaDTO toDTO(Empresa empresa) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(empresa.getId());
        dto.setNome(empresa.getNome());
        dto.setCnpj(empresa.getCnpj());
        dto.setEnderecoPrincipal(empresa.getEnderecoPrincipal());

        // Se a lista de clientes não for null, mapeia os IDs dos clientes
        dto.setClientes(empresa.getClientes() != null ? 
            empresa.getClientes().stream().map(Cliente::getId).collect(Collectors.toList()) : new ArrayList<>());

        return dto;
    }

    public Empresa toEntity(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());
        empresa.setEnderecoPrincipal(dto.getEnderecoPrincipal());
        
        // Mapeamento reverso de clientes, caso necessário
        return empresa;
    }
}
