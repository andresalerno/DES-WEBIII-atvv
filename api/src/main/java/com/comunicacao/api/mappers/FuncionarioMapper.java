package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.FuncionarioDTO;
import com.comunicacao.api.dtos.DocumentoDTO;
import com.comunicacao.api.dtos.TelefoneDTO;
import com.comunicacao.api.dtos.EnderecoDTO;
import com.comunicacao.api.modelos.Funcionario;
import com.comunicacao.api.modelos.Documento;
import com.comunicacao.api.modelos.Telefone;
import com.comunicacao.api.modelos.Endereco;
import com.comunicacao.api.modelos.Empresa;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuncionarioMapper {

    public FuncionarioDTO toDTO(Funcionario funcionario) {
        if (funcionario == null) return null;

        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setPerfil(funcionario.getPerfil());

        // Mapear documentos
        dto.setDocumentos(funcionario.getDocumentos() != null ?
            funcionario.getDocumentos().stream()
                .map(doc -> new DocumentoDTO(doc.getId(), doc.getTipo(), doc.getNumero()))
                .collect(Collectors.toList()) :
            List.of());

        // Mapear telefones
        dto.setTelefones(funcionario.getTelefones() != null ?
            funcionario.getTelefones().stream()
                .map(tel -> new TelefoneDTO(
                        tel.getId(),
                        tel.getTipo(),
                        tel.getNumero(),
                        tel.getCliente() != null ? tel.getCliente().getId() : null,
                        tel.getFuncionario() != null ? tel.getFuncionario().getId() : null
                ))
                .collect(Collectors.toList()) :
            List.of());

        // Mapear endereços
        dto.setEnderecos(funcionario.getEnderecos() != null ?
            funcionario.getEnderecos().stream()
                .map(end -> new EnderecoDTO(
                        end.getId(),
                        end.getTipo(),
                        end.getLogradouro(),
                        end.getNumero(),
                        end.getBairro(),
                        end.getCidade(),
                        end.getEstado(),
                        end.getCep(),
                        end.getCliente() != null ? end.getCliente().getId() : null
                ))
                .collect(Collectors.toList()) :
            List.of());

        // Empresa
        dto.setEmpresaId(funcionario.getEmpresa() != null ? funcionario.getEmpresa().getId() : null);

        return dto;
    }

    public Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) return null;

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getId());
        funcionario.setNome(dto.getNome());
        funcionario.setPerfil(dto.getPerfil());

        // Empresa (apenas ID)
        if (dto.getEmpresaId() != null) {
            Empresa empresa = new Empresa();
            empresa.setId(dto.getEmpresaId());
            funcionario.setEmpresa(empresa);
        }

        // Não mapeamos listas de documentos, telefones e endereços diretamente aqui para evitar persistência de dados inconsistentes
        return funcionario;
    }
}
