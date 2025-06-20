package com.comunicacao.api.repositorio;

import com.comunicacao.api.modelos.ServicoMercadoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoMercadoriaRepositorio extends JpaRepository<ServicoMercadoria, Long> {
	    
	List<ServicoMercadoria> findByEmpresaId(Long empresaId);

}
