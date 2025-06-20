package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.FuncionarioDTO;
import com.comunicacao.api.dtos.DocumentoDTO;
import com.comunicacao.api.dtos.TelefoneDTO;
import com.comunicacao.api.dtos.EnderecoDTO;
import com.comunicacao.api.modelos.Funcionario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuncionarioMapper {

    // Método para converter Funcionario para FuncionarioDTO
    public FuncionarioDTO toDTO(Funcionario funcionario) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setPerfil(funcionario.getPerfil());

        // Verifique se as listas são nulas antes de mapear
        dto.setDocumentos(funcionario.getDocumentos() != null ? 
                funcionario.getDocumentos().stream()
                        .map(doc -> new DocumentoDTO(doc.getId(), doc.getTipo(), doc.getNumero()))
                        .collect(Collectors.toList()) :
                List.of()); // Se for nulo, retorna uma lista vazia

        dto.setTelefones(funcionario.getTelefones() != null ? 
                funcionario.getTelefones().stream()
                        .map(tel -> new TelefoneDTO(tel.getId(), tel.getNumero()))
                        .collect(Collectors.toList()) :
                List.of()); // Se for nulo, retorna uma lista vazia

        dto.setEnderecos(funcionario.getEnderecos() != null ? 
                funcionario.getEnderecos().stream()
                        .map(end -> new EnderecoDTO(end.getId(), end.getTipo(), end.getLogradouro(), end.getNumero(), end.getBairro(), end.getCidade(), end.getEstado(), end.getCep()))
                        .collect(Collectors.toList()) :
                List.of()); // Se for nulo, retorna uma lista vazia

        // Mapear o ID da empresa
        dto.setEmpresaId(funcionario.getEmpresa() != null ? funcionario.getEmpresa().getId() : null);

        return dto;
    }

    // Método para converter FuncionarioDTO para Funcionario
    public Funcionario toEntity(FuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getId());
        funcionario.setNome(dto.getNome());
        funcionario.setPerfil(dto.getPerfil());

        // Adicionar mapeamento para documentos, telefones, e endereços se necessário

        return funcionario;
    }
}
