package com.comunicacao.api.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Venda;

public interface VendaRepositorio extends JpaRepository<Venda, Long> {
	// Métodos personalizados, se necessário
	 List<Venda> findByServicoMercadoria_Empresa_IdAndDataVendaBetween(Long empresaId, Date startDate, Date endDate);
}
