package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.DocumentoDTO;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Documento;
import com.comunicacao.api.modelos.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {

    // Converte Documento → DocumentoDTO
    public DocumentoDTO toDTO(Documento documento) {
        DocumentoDTO dto = new DocumentoDTO();
        dto.setId(documento.getId());
        dto.setTipo(documento.getTipo());
        dto.setNumero(documento.getNumero());

        dto.setClienteId(documento.getCliente() != null ? documento.getCliente().getId() : null);
        dto.setFuncionarioId(documento.getFuncionario() != null ? documento.getFuncionario().getId() : null);

        return dto;
    }

    // Converte DocumentoDTO → Documento
    public Documento toEntity(DocumentoDTO dto) {
        Documento documento = new Documento();
        documento.setId(dto.getId());
        documento.setTipo(dto.getTipo());
        documento.setNumero(dto.getNumero());

        if (dto.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            documento.setCliente(cliente);
        }

        if (dto.getFuncionarioId() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(dto.getFuncionarioId());
            documento.setFuncionario(funcionario);
        }

        return documento;
    }
}
