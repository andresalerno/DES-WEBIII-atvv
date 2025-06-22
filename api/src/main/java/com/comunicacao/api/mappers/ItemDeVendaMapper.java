package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.ItemDeVendaDTO;
import com.comunicacao.api.modelos.ItemDeVenda;
import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.modelos.Venda;
import org.springframework.stereotype.Component;

@Component
public class ItemDeVendaMapper {

    // Converte de entidade para DTO
    public ItemDeVendaDTO toDTO(ItemDeVenda item) {
        if (item == null) return null;

        ItemDeVendaDTO dto = new ItemDeVendaDTO();
        dto.setId(item.getId());
        dto.setQuantidade(item.getQuantidade());
        dto.setValorTotal(item.getValorTotal());

        // Segurança: verificação de nulo antes de acessar IDs
        dto.setServicoMercadoriaId(item.getServicoMercadoria() != null ? item.getServicoMercadoria().getId() : null);
        dto.setVendaId(item.getVenda() != null ? item.getVenda().getId() : null);

        return dto;
    }

    // Converte de DTO para entidade (apenas referências por ID)
    public ItemDeVenda toEntity(ItemDeVendaDTO dto) {
        if (dto == null) return null;

        ItemDeVenda item = new ItemDeVenda();
        item.setId(dto.getId());
        item.setQuantidade(dto.getQuantidade());
        item.setValorTotal(dto.getValorTotal());

        if (dto.getServicoMercadoriaId() != null) {
            ServicoMercadoria servico = new ServicoMercadoria();
            servico.setId(dto.getServicoMercadoriaId());
            item.setServicoMercadoria(servico);
        }

        if (dto.getVendaId() != null) {
            Venda venda = new Venda();
            venda.setId(dto.getVendaId());
            item.setVenda(venda);
        }

        return item;
    }
}
