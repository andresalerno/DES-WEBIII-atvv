package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.ItemDeVenda;

public interface ItemDeVendaRepositorio extends JpaRepository<ItemDeVenda, Long> {
	// Aqui você pode definir métodos personalizados de consulta, se necessário
	// Por exemplo:
	// List<ItemDeVenda> findByVendaId(Long vendaId);

}
