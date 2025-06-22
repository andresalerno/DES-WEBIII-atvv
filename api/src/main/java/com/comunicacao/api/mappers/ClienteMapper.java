package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.ClienteDTO;
import com.comunicacao.api.dtos.DocumentoDTO;
import com.comunicacao.api.dtos.EnderecoDTO;
import com.comunicacao.api.dtos.TelefoneDTO;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Documento;
import com.comunicacao.api.modelos.Endereco;
import com.comunicacao.api.modelos.Telefone;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());

        // Mapeia documentos completos
        dto.setDocumentos(cliente.getDocumentos() != null ?
            cliente.getDocumentos().stream().map(doc -> new DocumentoDTO(
                doc.getId(),
                doc.getTipo(),
                doc.getNumero(),
                doc.getCliente() != null ? doc.getCliente().getId() : null,
                doc.getFuncionario() != null ? doc.getFuncionario().getId() : null
            )).collect(Collectors.toList()) : List.of());

        // Mapeia telefones completos
        dto.setTelefones(cliente.getTelefones() != null ?
            cliente.getTelefones().stream().map(tel -> new TelefoneDTO(
                tel.getId(),
                tel.getTipo(),
                tel.getNumero(),
                tel.getCliente() != null ? tel.getCliente().getId() : null,
                tel.getFuncionario() != null ? tel.getFuncionario().getId() : null
            )).collect(Collectors.toList()) : List.of());

        // Mapeia endereços completos
        dto.setEnderecos(cliente.getEnderecos() != null ?
            cliente.getEnderecos().stream().map(end -> new EnderecoDTO(
                end.getId(),
                end.getTipo(),
                end.getLogradouro(),
                end.getNumero(),
                end.getBairro(),
                end.getCidade(),
                end.getEstado(),
                end.getCep(),
                end.getCliente() != null ? end.getCliente().getId() : null
            )).collect(Collectors.toList()) : List.of());

        dto.setEmpresaId(cliente.getEmpresa() != null ? cliente.getEmpresa().getId() : null);

        return dto;
    }

    public List<ClienteDTO> toDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        // Observação: documentos, telefones e endereços não são mapeados na ida (DTO -> Entidade) neste método simplificado.
        return cliente;
    }
}
