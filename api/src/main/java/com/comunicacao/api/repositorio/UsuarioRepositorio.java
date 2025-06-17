package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	// Define any custom query methods here if needed
	// For example:
	// Optional<Usuario> findByEmail(String email);

}
