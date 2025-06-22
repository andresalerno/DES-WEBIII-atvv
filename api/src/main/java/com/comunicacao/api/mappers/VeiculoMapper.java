package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.VeiculoDTO;
import com.comunicacao.api.modelos.Empresa;
import com.comunicacao.api.modelos.Veiculo;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    // Converte Veiculo para VeiculoDTO
    public VeiculoDTO toDTO(Veiculo veiculo) {
        if (veiculo == null) return null;

        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setModelo(veiculo.getModelo());
        dto.setMarca(veiculo.getMarca());
        dto.setPlaca(veiculo.getPlaca());
        dto.setEmpresaId(veiculo.getEmpresa() != null ? veiculo.getEmpresa().getId() : null);

        return dto;
    }

    // Converte VeiculoDTO para Veiculo
    public Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) return null;

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setModelo(dto.getModelo());
        veiculo.setMarca(dto.getMarca());
        veiculo.setPlaca(dto.getPlaca());

        // Criar entidade Empresa com o ID se presente
        if (dto.getEmpresaId() != null) {
            Empresa empresa = new Empresa();
            empresa.setId(dto.getEmpresaId());
            veiculo.setEmpresa(empresa);
        }

        return veiculo;
    }
}
