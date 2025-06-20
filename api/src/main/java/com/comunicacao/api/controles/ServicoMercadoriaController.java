package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.ServicoMercadoriaDTO;
import com.comunicacao.api.mappers.ServicoMercadoriaMapper;
import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.repositorio.ServicoMercadoriaRepositorio;

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

@Tag(name = "Controle Serviço / Mercadoria", description = "Gerencia os serviços e mercadorias do sistema")
@RestController
@RequestMapping("/servicos-mercadorias")
public class ServicoMercadoriaController {
	
	@Autowired
	private ServicoMercadoriaMapper servicoMercadoriaMapper;

    @Autowired
    private ServicoMercadoriaRepositorio servicoMercadoriaRepository;

 // Endpoint para listar todos os serviços/mercadorias
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ServicoMercadoriaDTO> listarServicosMercadorias() {
        List<ServicoMercadoria> servicosMercadorias = servicoMercadoriaRepository.findAll();
        // Mapear cada ServicoMercadoria para ServicoMercadoriaDTO
        return servicosMercadorias.stream()
                                   .map(servicoMercadoriaMapper::toDTO)
                                   .collect(Collectors.toList());
    }

 // Endpoint para buscar um serviço/mercadoria por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ServicoMercadoriaDTO> buscarServicoMercadoria(@PathVariable Long id) {
        Optional<ServicoMercadoria> servicoMercadoria = servicoMercadoriaRepository.findById(id);
        return servicoMercadoria.map(servico -> ResponseEntity.ok(servicoMercadoriaMapper.toDTO(servico)))
                               .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo serviço/mercadoria
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ServicoMercadoriaDTO> adicionarServicoMercadoria(@RequestBody @Valid ServicoMercadoriaDTO servicoMercadoriaDTO) {
        ServicoMercadoria servicoMercadoria = servicoMercadoriaMapper.toEntity(servicoMercadoriaDTO);
        ServicoMercadoria servicoMercadoriaSalvo = servicoMercadoriaRepository.save(servicoMercadoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoMercadoriaMapper.toDTO(servicoMercadoriaSalvo));
    }

 // Endpoint para atualizar um serviço/mercadoria existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ServicoMercadoriaDTO> atualizarServicoMercadoria(@PathVariable Long id, @RequestBody @Valid ServicoMercadoriaDTO servicoMercadoriaDTO) {
        if (servicoMercadoriaRepository.existsById(id)) {
            ServicoMercadoria servicoMercadoria = servicoMercadoriaMapper.toEntity(servicoMercadoriaDTO);
            servicoMercadoria.setId(id); // Atualiza o ID do serviço/mercadoria
            ServicoMercadoria servicoMercadoriaAtualizado = servicoMercadoriaRepository.save(servicoMercadoria);
            return ResponseEntity.ok(servicoMercadoriaMapper.toDTO(servicoMercadoriaAtualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um serviço/mercadoria
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServicoMercadoria(@PathVariable Long id) {
        if (servicoMercadoriaRepository.existsById(id)) {
            servicoMercadoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
    
    // Endpoint para listar todos os serviços e mercadorias por empresa (loja)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<ServicoMercadoriaDTO>> listarServicosMercadoriasPorEmpresa(@PathVariable Long empresaId) {
        List<ServicoMercadoria> servicosMercadorias = servicoMercadoriaRepository.findByEmpresaId(empresaId);
        if (servicosMercadorias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Se não encontrar, retorna 404
        } else {
            // Mapear os serviços/mercadorias para DTO
            List<ServicoMercadoriaDTO> dtoList = servicosMercadorias.stream()
                                                                    .map(servicoMercadoriaMapper::toDTO)
                                                                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);  // Retorna a lista com status 200
        }
    }
}