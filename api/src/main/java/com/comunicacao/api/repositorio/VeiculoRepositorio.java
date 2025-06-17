package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Veiculo;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {

	// Define any additional query methods if needed
	// For example:
	// List<Veiculo> findByMarca(String marca);

}
