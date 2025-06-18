package com.comunicacao.api.controles;

import com.comunicacao.api.modelos.Documento;
import com.comunicacao.api.repositorio.DocumentoRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Controle Documento", description = "Gerencia os documentos do sistema")
@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoRepositorio documentoRepository;

    // Endpoint para listar todos os documentos
    @GetMapping
    public List<Documento> listarDocumentos() {
        return documentoRepository.findAll();
    }

    // Endpoint para buscar um documento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscarDocumento(@PathVariable Long id) {
        Optional<Documento> documento = documentoRepository.findById(id);
        return documento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo documento
    @PostMapping
    public ResponseEntity<Documento> adicionarDocumento(@RequestBody @Valid Documento documento) {
        Documento documentoSalvo = documentoRepository.save(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoSalvo);
    }

    // Endpoint para atualizar um documento existente
    @PutMapping("/{id}")
    public ResponseEntity<Documento> atualizarDocumento(@PathVariable Long id, @RequestBody @Valid Documento documento) {
        if (documentoRepository.existsById(id)) {
            documento.setId(id); // Atualiza o ID
            Documento documentoAtualizado = documentoRepository.save(documento);
            return ResponseEntity.ok(documentoAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um documento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDocumento(@PathVariable Long id) {
        if (documentoRepository.existsById(id)) {
            documentoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
}
