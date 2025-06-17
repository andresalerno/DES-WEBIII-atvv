package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Documento;

public interface DocumentoRepositorio extends JpaRepository<Documento, Long> {

	// Define any custom query methods here if needed
	// For example:
	// List<Documento> findByTituloContaining(String titulo);

}
