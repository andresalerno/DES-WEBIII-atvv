package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Telefone;

public interface TelefoneRepositorio extends JpaRepository<Telefone, Long> {

	// Define any custom query methods if needed
	// For example:
	// List<Telefone> findByUsuarioId(Long usuarioId);

}
