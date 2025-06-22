package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.FuncionarioDTO;
import com.comunicacao.api.mappers.FuncionarioMapper;
import com.comunicacao.api.modelos.Funcionario;
import com.comunicacao.api.repositorio.EmpresaRepositorio;
import com.comunicacao.api.repositorio.FuncionarioRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Controle Funcionários", description = "Gerencia os funcionários do sistema")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioMapper funcionarioMapper;

    @Autowired
    private FuncionarioRepositorio funcionarioRepository;
    
    @Autowired
    private EmpresaRepositorio empresaRepository;

    // Endpoint para listar todos os funcionários
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<FuncionarioDTO> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        // Converter para DTO antes de retornar
        return funcionarios.stream()
                .map(funcionarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Endpoint para buscar um funcionário por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarFuncionario(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        // Se encontrado, converter para DTO e retornar
        return funcionario.map(funcionarioMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo funcionário
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FuncionarioDTO> adicionarFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO) {
        // Converter DTO para entidade
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        // Converter a entidade salva para DTO e retornar
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(funcionarioMapper.toDTO(funcionarioSalvo));
    }

    // Endpoint para atualizar um funcionário existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody @Valid FuncionarioDTO funcionarioDTO) {
        if (funcionarioRepository.existsById(id)) {
            Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
            funcionario.setId(id); // Atualiza o ID do funcionário
            Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);
            // Converter para DTO e retornar
            return ResponseEntity.ok(funcionarioMapper.toDTO(funcionarioAtualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um funcionário
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
    
    // Endpoint para listar todos os funcionários de uma empresa
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    @Tag(name = "🚨 Endpoints Críticos")
    @Operation(
        summary = "🚨 [TAREFA] 2.Listar funcionários por empresa",
        description = "⚠️ Este endpoint retorna todos os funcionários de uma empresa específica."
    )
    @GetMapping("/empresa/{empresaId}/funcionarios")
    public ResponseEntity<List<FuncionarioDTO>> listarFuncionariosPorEmpresa(@PathVariable Long empresaId) {
        List<Funcionario> funcionarios = funcionarioRepository.findByEmpresa_Id(empresaId);

        if (funcionarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<FuncionarioDTO> funcionarioDTOs = funcionarios.stream()
            .map(funcionarioMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(funcionarioDTOs);
    }


}
