package com.comunicacao.api.controles;


import com.comunicacao.api.modelos.Empresa;
import com.comunicacao.api.repositorio.EmpresaRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Controle Empresa", description = "Gerencia as empresas do sistema")
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepositorio empresaRepository;

    // Endpoint para listar todas as empresas
    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    // Endpoint para buscar uma empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar uma nova empresa
    @PostMapping
    public ResponseEntity<Empresa> adicionarEmpresa(@RequestBody @Valid Empresa empresa) {
        Empresa empresaSalva = empresaRepository.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaSalva);
    }

    // Endpoint para atualizar uma empresa existente
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody @Valid Empresa empresa) {
        if (empresaRepository.existsById(id)) {
            empresa.setId(id); // Atualiza o ID da empresa
            Empresa empresaAtualizada = empresaRepository.save(empresa);
            return ResponseEntity.ok(empresaAtualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir uma empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmpresa(@PathVariable Long id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
}
