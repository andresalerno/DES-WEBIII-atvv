package com.comunicacao.api.repositorio;

import com.comunicacao.api.modelos.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    @EntityGraph(attributePaths = {"documentos", "telefones", "enderecos"})
    List<Cliente> findByEmpresa_Id(Long empresaId);
}
