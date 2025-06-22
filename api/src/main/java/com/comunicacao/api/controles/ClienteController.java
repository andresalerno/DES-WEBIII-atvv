package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.ClienteDTO;
import com.comunicacao.api.mappers.ClienteMapper;
import com.comunicacao.api.mock.DataMock;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.repositorio.ClienteRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Controle Cliente", description = "Gerencia os clientes do sistema")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteMapper clienteMapper;
	
	@Autowired
    private ClienteRepositorio clienteRepository;

	// Endpoint para listar todos os clientes
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOs = clientes.stream()
                                              .map(clienteMapper::toDTO)
                                              .collect(Collectors.toList());
        return ResponseEntity.ok(clienteDTOs);
    }

 // Endpoint para buscar um cliente por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(c -> ResponseEntity.ok(clienteMapper.toDTO(c)))
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo cliente
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClienteDTO> adicionarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        ClienteDTO clienteResposta = clienteMapper.toDTO(clienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResposta);
    }


 // Endpoint para atualizar um cliente existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        if (clienteRepository.existsById(id)) {
            Cliente cliente = clienteMapper.toEntity(clienteDTO);  // Converte ClienteDTO para Cliente
            cliente.setId(id);  // Atualiza o ID antes de salvar
            Cliente clienteAtualizado = clienteRepository.save(cliente);
            ClienteDTO clienteResposta = clienteMapper.toDTO(clienteAtualizado);  // Converte de volta para ClienteDTO
            return ResponseEntity.ok(clienteResposta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


 // Endpoint para excluir um cliente
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    @Tag(name = "🚨 Endpoints Críticos")
    @Operation(
        summary = "🚨 [TAREFA] 1.Listar clientes por empresa",
        description = "⚠️ Este endpoint retorna todos os clientes de uma empresa específica."
    )
    @GetMapping("/empresa/{empresaId}/clientes")
    public ResponseEntity<List<ClienteDTO>> listarClientesPorEmpresa(@PathVariable Long empresaId) {
        List<Cliente> clientes = clienteRepository.findByEmpresa_Id(empresaId);
        return ResponseEntity.ok(clienteMapper.toDTO(clientes));
    }














}