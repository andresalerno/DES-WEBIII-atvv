package com.comunicacao.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

	List<Cliente> findByEmpresaId(Long empresaId);
	
	@EntityGraph(attributePaths = {"documentos", "telefones", "enderecos"})
    Cliente findClienteById(Long id);

	// Define any custom query methods here if needed
	// For example:
	// List<Cliente> findByNome(String nome);

}
