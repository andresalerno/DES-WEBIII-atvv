package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.VendaDTO;
import com.comunicacao.api.modelos.Venda;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.ServicoMercadoria;
import org.springframework.stereotype.Component;

@Component
public class VendaMapper {

    // Converte Venda para VendaDTO
    public VendaDTO toDTO(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.setId(venda.getId());
        dto.setValor(venda.getValor());
        dto.setDataVenda(venda.getDataVenda());

        // Mapeia o ID do serviço mercadoria e cliente (não a entidade inteira)
        if (venda.getServicoMercadoria() != null) {
            dto.setServicoMercadoriaId(venda.getServicoMercadoria().getId());
        }

        if (venda.getCliente() != null) {
            dto.setClienteId(venda.getCliente().getId());
        }

        return dto;
    }

    // Converte VendaDTO para Venda
    public Venda toEntity(VendaDTO dto) {
        Venda venda = new Venda();
        venda.setId(dto.getId());
        venda.setValor(dto.getValor());
        venda.setDataVenda(dto.getDataVenda());

        // Mapeia as entidades com base nos IDs (se necessário)
        if (dto.getServicoMercadoriaId() != null) {
            ServicoMercadoria servicoMercadoria = new ServicoMercadoria();
            servicoMercadoria.setId(dto.getServicoMercadoriaId());
            venda.setServicoMercadoria(servicoMercadoria);
        }

        if (dto.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            venda.setCliente(cliente);
        }

        return venda;
    }
}
