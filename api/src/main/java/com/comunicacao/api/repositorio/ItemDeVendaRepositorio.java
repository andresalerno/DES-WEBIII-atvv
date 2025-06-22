package com.comunicacao.api.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.comunicacao.api.modelos.ItemDeVenda;

public interface ItemDeVendaRepositorio extends JpaRepository<ItemDeVenda, Long> {
    List<ItemDeVenda> findByVenda_Id(Long vendaId);
}
