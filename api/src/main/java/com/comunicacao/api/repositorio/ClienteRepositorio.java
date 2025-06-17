package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

	// Define any custom query methods here if needed
	// For example:
	// List<Cliente> findByNome(String nome);

}
