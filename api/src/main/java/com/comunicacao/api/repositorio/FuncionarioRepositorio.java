package com.comunicacao.api.repositorio;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comunicacao.api.modelos.Funcionario;

import java.util.List;
import java.util.Set;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

	@EntityGraph(attributePaths = {"documentos", "telefones", "enderecos"})
	List<Funcionario> findByEmpresa_Id(Long empresaId);

}
