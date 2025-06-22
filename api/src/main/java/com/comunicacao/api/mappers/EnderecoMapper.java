package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.EnderecoDTO;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    // Converte DTO para entidade
    public Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) return null;

        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setTipo(dto.getTipo());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());

        if (dto.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            endereco.setCliente(cliente); // relação será gerenciada pelo controller ou service
        }

        return endereco;
    }

    // Converte entidade para DTO
    public EnderecoDTO toDTO(Endereco entity) {
        if (entity == null) return null;

        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setCep(entity.getCep());
        dto.setClienteId(entity.getCliente() != null ? entity.getCliente().getId() : null);

        return dto;
    }
}
