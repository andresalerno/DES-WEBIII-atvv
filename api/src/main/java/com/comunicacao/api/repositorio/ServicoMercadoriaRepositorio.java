package com.comunicacao.api.repositorio;

import com.comunicacao.api.modelos.ServicoMercadoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoMercadoriaRepositorio extends JpaRepository<ServicoMercadoria, Long> {
	    // Métodos personalizados, se necessário

}
