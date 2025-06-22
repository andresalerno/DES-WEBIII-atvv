package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.VendaDTO;
import com.comunicacao.api.dtos.ItemDeVendaDTO;
import com.comunicacao.api.modelos.Venda;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.modelos.ItemDeVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendaMapper {

    @Autowired
    private ItemDeVendaMapper itemMapper;

    public VendaDTO toDTO(Venda venda) {
        if (venda == null) return null;

        VendaDTO dto = new VendaDTO();
        dto.setId(venda.getId());
        dto.setValor(venda.getValor());
        dto.setDataVenda(venda.getDataVenda());
        dto.setClienteId(venda.getCliente() != null ? venda.getCliente().getId() : null);
        dto.setServicoMercadoriaId(venda.getServicoMercadoria() != null ? venda.getServicoMercadoria().getId() : null);

        dto.setItens(venda.getItens() != null ?
            venda.getItens().stream().map(itemMapper::toDTO).collect(Collectors.toList()) :
            List.of());
        
        dto.setEmpresaId(
        	    venda.getServicoMercadoria() != null && 
        	    venda.getServicoMercadoria().getEmpresa() != null 
        	        ? venda.getServicoMercadoria().getEmpresa().getId() 
        	        : null
        	);



        return dto;
    }

    public Venda toEntity(VendaDTO dto) {
        if (dto == null) return null;

        Venda venda = new Venda();
        venda.setId(dto.getId());
        venda.setValor(dto.getValor());
        venda.setDataVenda(dto.getDataVenda());

        if (dto.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            venda.setCliente(cliente);
        }

        if (dto.getServicoMercadoriaId() != null) {
            ServicoMercadoria servico = new ServicoMercadoria();
            servico.setId(dto.getServicoMercadoriaId());
            venda.setServicoMercadoria(servico);
        }

        if (dto.getItens() != null) {
            List<ItemDeVenda> itens = dto.getItens().stream()
                .map(itemMapper::toEntity)
                .peek(item -> item.setVenda(venda)) // vincula o item à venda
                .toList();
            venda.setItens(itens);
        }

        return venda;
    }
}
