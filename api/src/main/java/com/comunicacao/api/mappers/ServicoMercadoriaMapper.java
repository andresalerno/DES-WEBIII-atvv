package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.ServicoMercadoriaDTO;
import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.modelos.Empresa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicoMercadoriaMapper {

    // Converte entidade para DTO
    public ServicoMercadoriaDTO toDTO(ServicoMercadoria entidade) {
        if (entidade == null) return null;

        return new ServicoMercadoriaDTO(
            entidade.getId(),
            entidade.getNome(),
            entidade.getDescricao(),
            entidade.getValor(),
            entidade.getDataCadastro(),
            entidade.getTipo(),
            entidade.getEmpresa() != null ? entidade.getEmpresa().getId() : null
        );
    }

    // Converte lista de entidades para lista de DTOs
    public List<ServicoMercadoriaDTO> toDTO(List<ServicoMercadoria> entidades) {
        if (entidades == null) return null;
        return entidades.stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList());
    }

    // Converte DTO para entidade
    public ServicoMercadoria toEntity(ServicoMercadoriaDTO dto) {
        if (dto == null) return null;

        ServicoMercadoria entidade = new ServicoMercadoria();
        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
        entidade.setValor(dto.getValor());
        entidade.setDataCadastro(dto.getDataCadastro());
        entidade.setTipo(dto.getTipo());

        if (dto.getEmpresaId() != null) {
            Empresa empresa = new Empresa();
            empresa.setId(dto.getEmpresaId());
            entidade.setEmpresa(empresa);
        }

        return entidade;
    }
}
