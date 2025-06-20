package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.ServicoMercadoriaDTO;
import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.modelos.Empresa;
import org.springframework.stereotype.Component;

@Component
public class ServicoMercadoriaMapper {

    // Converte ServicoMercadoria para ServicoMercadoriaDTO
    public ServicoMercadoriaDTO toDTO(ServicoMercadoria servicoMercadoria) {
        ServicoMercadoriaDTO dto = new ServicoMercadoriaDTO(
            servicoMercadoria.getId(),
            servicoMercadoria.getNome(),
            servicoMercadoria.getDescricao(),
            servicoMercadoria.getValor(),
            servicoMercadoria.getDataCadastro(),
            servicoMercadoria.getTipo(),
            servicoMercadoria.getEmpresa() != null ? servicoMercadoria.getEmpresa().getId() : null // Mapeia o ID da empresa
        );
        return dto;
    }

    // Converte ServicoMercadoriaDTO para ServicoMercadoria
    public ServicoMercadoria toEntity(ServicoMercadoriaDTO dto) {
        ServicoMercadoria servicoMercadoria = new ServicoMercadoria();
        servicoMercadoria.setId(dto.getId());
        servicoMercadoria.setNome(dto.getNome());
        servicoMercadoria.setDescricao(dto.getDescricao());
        servicoMercadoria.setValor(dto.getValor());
        servicoMercadoria.setDataCadastro(dto.getDataCadastro());
        servicoMercadoria.setTipo(dto.getTipo());

        // Mapeia o ID da empresa para a entidade Empresa (cria a instância da empresa)
        if (dto.getEmpresaId() != null) {
            Empresa empresa = new Empresa();
            empresa.setId(dto.getEmpresaId()); // Definindo apenas o ID da empresa, sem carregar a entidade inteira
            servicoMercadoria.setEmpresa(empresa);
        }

        return servicoMercadoria;
    }
}
