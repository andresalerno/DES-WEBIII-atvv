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
        if (telefone == null) return null;

        return new TelefoneDTO(
            telefone.getId(),
            telefone.getTipo(),
            telefone.getNumero(),
            telefone.getCliente() != null ? telefone.getCliente().getId() : null,
            telefone.getFuncionario() != null ? telefone.getFuncionario().getId() : null
        );
    }

    // Converte TelefoneDTO para Telefone
    public Telefone toEntity(TelefoneDTO dto) {
        if (dto == null) return null;

        Telefone telefone = new Telefone();
        telefone.setId(dto.getId());
        telefone.setTipo(dto.getTipo());
        telefone.setNumero(dto.getNumero());

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
