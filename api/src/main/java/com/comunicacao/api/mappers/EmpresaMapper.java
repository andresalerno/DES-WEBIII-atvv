package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.EmpresaDTO;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Empresa;
import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.modelos.Veiculo;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpresaMapper {

    private final FuncionarioMapper funcionarioMapper;

    public EmpresaMapper(FuncionarioMapper funcionarioMapper) {
        this.funcionarioMapper = funcionarioMapper;
    }

    public EmpresaDTO toDTO(Empresa empresa) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(empresa.getId());
        dto.setNome(empresa.getNome());
        dto.setCnpj(empresa.getCnpj());
        dto.setEnderecoPrincipal(empresa.getEnderecoPrincipal());

        dto.setClientes(empresa.getClientes() != null ?
            empresa.getClientes().stream().map(Cliente::getId).collect(Collectors.toList()) :
            Collections.emptyList());

        dto.setServicosMercadorias(empresa.getServicosMercadorias() != null ?
            empresa.getServicosMercadorias().stream().map(ServicoMercadoria::getId).collect(Collectors.toList()) :
            Collections.emptyList());

        dto.setVeiculos(empresa.getVeiculos() != null ?
            empresa.getVeiculos().stream().map(Veiculo::getId).collect(Collectors.toList()) :
            Collections.emptyList());

        dto.setFuncionarios(empresa.getFuncionarios() != null ?
            empresa.getFuncionarios().stream().map(funcionarioMapper::toDTO).collect(Collectors.toList()) :
            Collections.emptyList());

        return dto;
    }

    public Empresa toEntity(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());
        empresa.setEnderecoPrincipal(dto.getEnderecoPrincipal());

        empresa.setClientes(dto.getClientes() != null ?
            dto.getClientes().stream().map(id -> {
                Cliente cliente = new Cliente();
                cliente.setId(id);
                return cliente;
            }).collect(Collectors.toList()) :
            Collections.emptyList());

        empresa.setServicosMercadorias(dto.getServicosMercadorias() != null ?
            dto.getServicosMercadorias().stream().map(id -> {
                ServicoMercadoria servico = new ServicoMercadoria();
                servico.setId(id);
                return servico;
            }).collect(Collectors.toList()) :
            Collections.emptyList());

        empresa.setVeiculos(dto.getVeiculos() != null ?
            dto.getVeiculos().stream().map(id -> {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(id);
                return veiculo;
            }).collect(Collectors.toList()) :
            Collections.emptyList());

        empresa.setFuncionarios(dto.getFuncionarios() != null ?
            dto.getFuncionarios().stream().map(funcionarioMapper::toEntity).collect(Collectors.toList()) :
            Collections.emptyList());

        return empresa;
    }
}
