package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

	// Define any custom query methods if needed
	// For example:
	// List<Endereco> findByCidade(String cidade);

}
