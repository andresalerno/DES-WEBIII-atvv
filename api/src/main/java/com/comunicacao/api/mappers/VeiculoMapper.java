package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.VeiculoDTO;
import com.comunicacao.api.modelos.Veiculo;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    // Converte Veiculo para VeiculoDTO
    public VeiculoDTO toDTO(Veiculo veiculo) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setModelo(veiculo.getModelo());
        dto.setMarca(veiculo.getMarca());
        dto.setPlaca(veiculo.getPlaca());

        // Verificar se a Empresa não é null antes de acessar o ID
        if (veiculo.getEmpresa() != null) {
            dto.setEmpresaId(veiculo.getEmpresa().getId());  // Mapear o ID da empresa associada
        } else {
            dto.setEmpresaId(null);  // Caso contrário, definimos como null
        }

        return dto;
    }

    // Converte VeiculoDTO para Veiculo
    public Veiculo toEntity(VeiculoDTO dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setModelo(dto.getModelo());
        veiculo.setMarca(dto.getMarca());
        veiculo.setPlaca(dto.getPlaca());
        // Aqui, é possível mapear a Empresa pelo ID (caso precise associar a entidade da Empresa)
        return veiculo;
    }
}
