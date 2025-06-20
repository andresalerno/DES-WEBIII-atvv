package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.ItemDeVendaDTO;
import com.comunicacao.api.modelos.ItemDeVenda;
import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.modelos.Venda;
import org.springframework.stereotype.Component;

@Component
public class ItemDeVendaMapper {

    // Método para converter ItemDeVenda para ItemDeVendaDTO
    public ItemDeVendaDTO toDTO(ItemDeVenda itemDeVenda) {
        ItemDeVendaDTO dto = new ItemDeVendaDTO();
        dto.setId(itemDeVenda.getId());
        dto.setServicoMercadoriaId(itemDeVenda.getServicoMercadoria().getId());  // Mapeando o ID do ServicoMercadoria
        dto.setVendaId(itemDeVenda.getVenda().getId());  // Mapeando o ID da Venda
        dto.setQuantidade(itemDeVenda.getQuantidade());
        dto.setValorTotal(itemDeVenda.getValorTotal());
        return dto;
    }

    // Método para converter ItemDeVendaDTO para ItemDeVenda
    public ItemDeVenda toEntity(ItemDeVendaDTO dto) {
        ItemDeVenda itemDeVenda = new ItemDeVenda();
        itemDeVenda.setId(dto.getId());
        
        // Aqui, é necessário buscar a entidade ServicoMercadoria e Venda usando os IDs
        ServicoMercadoria servicoMercadoria = new ServicoMercadoria();
        servicoMercadoria.setId(dto.getServicoMercadoriaId());
        itemDeVenda.setServicoMercadoria(servicoMercadoria);
        
        Venda venda = new Venda();
        venda.setId(dto.getVendaId());
        itemDeVenda.setVenda(venda);
        
        itemDeVenda.setQuantidade(dto.getQuantidade());
        itemDeVenda.setValorTotal(dto.getValorTotal());

        return itemDeVenda;
    }
}
