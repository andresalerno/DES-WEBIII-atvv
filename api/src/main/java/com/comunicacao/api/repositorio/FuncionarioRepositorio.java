package com.comunicacao.api.repositorio;


import com.comunicacao.api.modelos.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {
    // Métodos personalizados, se necessário
}
