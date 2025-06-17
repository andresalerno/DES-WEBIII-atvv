package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {

	// Define any custom query methods here if needed
	// For example:
	// List<Empresa> findByNome(String nome);

}
