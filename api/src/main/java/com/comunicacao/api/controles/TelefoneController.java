package com.comunicacao.api.controles;

import com.comunicacao.api.modelos.Telefone;
import com.comunicacao.api.repositorio.TelefoneRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Controle Telefone", description = "Gerencia os telefones do sistema")
@RestController
@RequestMapping("/telefones")
public class TelefoneController {

    @Autowired
    private TelefoneRepositorio telefoneRepository;

    // Endpoint para listar todos os telefones
    @GetMapping
    public List<Telefone> listarTelefones() {
        return telefoneRepository.findAll();
    }

    // Endpoint para buscar um telefone por ID
    @GetMapping("/{id}")
    public ResponseEntity<Telefone> buscarTelefone(@PathVariable Long id) {
        Optional<Telefone> telefone = telefoneRepository.findById(id);
        return telefone.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo telefone
    @PostMapping
    public ResponseEntity<Telefone> adicionarTelefone(@RequestBody @Valid Telefone telefone) {
        Telefone telefoneSalvo = telefoneRepository.save(telefone);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneSalvo);
    }

    // Endpoint para atualizar um telefone existente
    @PutMapping("/{id}")
    public ResponseEntity<Telefone> atualizarTelefone(@PathVariable Long id, @RequestBody @Valid Telefone telefone) {
        if (telefoneRepository.existsById(id)) {
            telefone.setId(id); // Atualiza o ID do telefone
            Telefone telefoneAtualizado = telefoneRepository.save(telefone);
            return ResponseEntity.ok(telefoneAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um telefone
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
