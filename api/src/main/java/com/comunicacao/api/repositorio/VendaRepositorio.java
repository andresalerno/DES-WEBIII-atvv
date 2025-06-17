package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Venda;

public interface VendaRepositorio extends JpaRepository<Venda, Long> {
	// Métodos personalizados, se necessário

}
