package com.comunicacao.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Veiculo;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {

	List<Veiculo> findByEmpresaId(Long empresaId);
	// Define any additional query methods if need	ed
	// For example:
	// List<Veiculo> findByMarca(String marca);

}
