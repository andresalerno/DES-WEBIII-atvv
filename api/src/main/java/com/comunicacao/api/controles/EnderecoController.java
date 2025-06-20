package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.EnderecoDTO;
import com.comunicacao.api.mappers.EnderecoMapper;
import com.comunicacao.api.mock.DataMock;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Endereco;
import com.comunicacao.api.repositorio.ClienteRepositorio;
import com.comunicacao.api.repositorio.EnderecoRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Controle Endereço", description = "Gerencia os endereços dos clientes do sistema")
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private ClienteRepositorio clienteRepository;
	
	@Autowired
	private EnderecoMapper enderecoMapper;

    @Autowired
    private EnderecoRepositorio enderecoRepository;

 // Endpoint para listar todos os endereços
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOs = enderecos.stream()
                                                  .map(enderecoMapper::toDTO)
                                                  .collect(Collectors.toList());
        return ResponseEntity.ok(enderecoDTOs);
    }

 // Endpoint para buscar um endereço por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarEndereco(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.map(e -> ResponseEntity.ok(enderecoMapper.toDTO(e)))
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EnderecoDTO> adicionarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        // Verificar se o cliente existe
        Cliente cliente = clienteRepository.findById(enderecoDTO.getClienteId()).orElse(null);
        
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Cliente não encontrado
        }
        
        // Mapear o DTO para a entidade
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        endereco.setCliente(cliente);  // Definir o cliente no endereço
        
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        
        // Retornar o DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoMapper.toDTO(enderecoSalvo));
    }
    
    

    public List<Cliente> gerarClientesMockados(int quantidadeClientes) {
        List<Cliente> clientes = new ArrayList<>();
        
        // Gerar clientes mockados
        for (int i = 1; i <= quantidadeClientes; i++) {
            Cliente cliente = new Cliente();
            cliente.setId((long) i);  // Gerar um ID único para cada cliente
            cliente.setNome("Cliente " + i);
            cliente.setEmail("cliente" + i + "@example.com");
            
            // Adicionar o cliente à lista
            clientes.add(cliente);
        }
        
        return clientes;
    }





    // Endpoint para atualizar um endereço existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        if (enderecoRepository.existsById(id)) {
            Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
            endereco.setId(id);
            Endereco enderecoAtualizado = enderecoRepository.save(endereco);
            EnderecoDTO enderecoResposta = enderecoMapper.toDTO(enderecoAtualizado);
            return ResponseEntity.ok(enderecoResposta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um endereço
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
}
