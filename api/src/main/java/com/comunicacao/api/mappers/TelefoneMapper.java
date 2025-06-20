package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.TelefoneDTO;
import com.comunicacao.api.modelos.Telefone;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Funcionario;

import org.springframework.stereotype.Component;

@Component
public class TelefoneMapper {

    // Converte Telefone para TelefoneDTO
    public TelefoneDTO toDTO(Telefone telefone) {
        TelefoneDTO dto = new TelefoneDTO();
        dto.setId(telefone.getId());
        dto.setTipo(telefone.getTipo());
        dto.setNumero(telefone.getNumero());

        // Apenas o ID do cliente e funcionário, não a entidade inteira
        if (telefone.getCliente() != null) {
            dto.setClienteId(telefone.getCliente().getId());
        }

        if (telefone.getFuncionario() != null) {
            dto.setFuncionarioId(telefone.getFuncionario().getId());
        }

        return dto;
    }

    // Converte TelefoneDTO para Telefone
    public Telefone toEntity(TelefoneDTO dto) {
        Telefone telefone = new Telefone();
        telefone.setId(dto.getId());
        telefone.setTipo(dto.getTipo());
        telefone.setNumero(dto.getNumero());

        // Converte apenas os IDs para entidades, caso necessário
        if (dto.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            telefone.setCliente(cliente);
        }

        if (dto.getFuncionarioId() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(dto.getFuncionarioId());
            telefone.setFuncionario(funcionario);
        }

        return telefone;
    }
}
