package com.comunicacao.api.repositorio;


import com.comunicacao.api.modelos.Funcionario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {
 
	List<Funcionario> findByEmpresa_Id(Long empresaId);// Métodos personalizados, se necessário
}
