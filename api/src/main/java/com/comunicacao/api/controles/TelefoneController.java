package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.TelefoneDTO;
import com.comunicacao.api.mappers.TelefoneMapper;
import com.comunicacao.api.modelos.Telefone;
import com.comunicacao.api.repositorio.TelefoneRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Controle Telefone", description = "Gerencia os telefones do sistema")
@RestController
@RequestMapping("/telefones")
public class TelefoneController {

    @Autowired
    private TelefoneRepositorio telefoneRepository;

    @Autowired
    private TelefoneMapper telefoneMapper;

    // Endpoint para listar todos os telefones
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<TelefoneDTO> listarTelefones() {
        List<Telefone> telefones = telefoneRepository.findAll();
        // Mapear cada Telefone para TelefoneDTO
        return telefones.stream()
                        .map(telefoneMapper::toDTO)
                        .collect(Collectors.toList());
    }

    // Endpoint para buscar um telefone por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TelefoneDTO> buscarTelefone(@PathVariable Long id) {
        Optional<Telefone> telefone = telefoneRepository.findById(id);
        return telefone.map(t -> ResponseEntity.ok(telefoneMapper.toDTO(t)))
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo telefone
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TelefoneDTO> adicionarTelefone(@RequestBody @Valid TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneMapper.toEntity(telefoneDTO);
        Telefone telefoneSalvo = telefoneRepository.save(telefone);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneMapper.toDTO(telefoneSalvo));
    }

    // Endpoint para atualizar um telefone existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@PathVariable Long id, @RequestBody @Valid TelefoneDTO telefoneDTO) {
        if (telefoneRepository.existsById(id)) {
            Telefone telefone = telefoneMapper.toEntity(telefoneDTO);
            telefone.setId(id); // Atualiza o ID do telefone
            Telefone telefoneAtualizado = telefoneRepository.save(telefone);
            return ResponseEntity.ok(telefoneMapper.toDTO(telefoneAtualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um telefone
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTelefone(@PathVariable Long id) {
        if (telefoneRepository.existsById(id)) {
            telefoneRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
}
