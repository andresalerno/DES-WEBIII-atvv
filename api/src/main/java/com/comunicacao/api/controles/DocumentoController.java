package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.DocumentoDTO;
import com.comunicacao.api.mappers.DocumentoMapper;
import com.comunicacao.api.modelos.Documento;
import com.comunicacao.api.repositorio.DocumentoRepositorio;

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

@Tag(name = "Controle Documento", description = "Gerencia os documentos do sistema")
@RestController
@RequestMapping("/documentos")
public class DocumentoController {

	@Autowired
    private DocumentoMapper documentoMapper;
	
	@Autowired
    private DocumentoRepositorio documentoRepository;

    // Endpoint para listar todos os documentos
	// Endpoint para listar todos os documentos
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> listarDocumentos() {
        List<Documento> documentos = documentoRepository.findAll();
        List<DocumentoDTO> documentoDTOs = documentos.stream()
                                                    .map(documentoMapper::toDTO)
                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(documentoDTOs);
    }

    // Endpoint para buscar um documento por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> buscarDocumento(@PathVariable Long id) {
        Optional<Documento> documento = documentoRepository.findById(id);
        return documento.map(d -> ResponseEntity.ok(documentoMapper.toDTO(d)))
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

 // Endpoint para adicionar um novo documento
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DocumentoDTO> adicionarDocumento(@RequestBody @Valid DocumentoDTO documentoDTO) {
        Documento documento = documentoMapper.toEntity(documentoDTO);
        Documento documentoSalvo = documentoRepository.save(documento);
        DocumentoDTO documentoResposta = documentoMapper.toDTO(documentoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoResposta);
    }

 // Endpoint para atualizar um documento existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDTO> atualizarDocumento(@PathVariable Long id, @RequestBody @Valid DocumentoDTO documentoDTO) {
        if (documentoRepository.existsById(id)) {
            Documento documento = documentoMapper.toEntity(documentoDTO);
            documento.setId(id);
            Documento documentoAtualizado = documentoRepository.save(documento);
            DocumentoDTO documentoResposta = documentoMapper.toDTO(documentoAtualizado);
            return ResponseEntity.ok(documentoResposta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um documento
    @PreAuthorize("hasRole('ADMIN')")
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