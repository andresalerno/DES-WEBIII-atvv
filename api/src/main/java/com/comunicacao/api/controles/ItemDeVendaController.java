package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.ItemDeVendaDTO;
import com.comunicacao.api.mappers.ItemDeVendaMapper;
import com.comunicacao.api.modelos.ItemDeVenda;
import com.comunicacao.api.repositorio.ItemDeVendaRepositorio;

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

@Tag(name = "Controle Itens de Venda", description = "Gerencia os itens de venda do sistema")
@RestController
@RequestMapping("/itens-venda")
public class ItemDeVendaController {
	
	@Autowired
	private ItemDeVendaMapper itemDeVendaMapper;

    @Autowired
    private ItemDeVendaRepositorio itemDeVendaRepository;

 // Endpoint para listar todos os itens de venda
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ItemDeVendaDTO> listarItensDeVenda() {
        List<ItemDeVenda> itensDeVenda = itemDeVendaRepository.findAll();
        // Mapear cada ItemDeVenda para ItemDeVendaDTO
        return itensDeVenda.stream()
                           .map(itemDeVendaMapper::toDTO)
                           .collect(Collectors.toList());
    }

 // Endpoint para buscar um item de venda por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ItemDeVendaDTO> buscarItemDeVenda(@PathVariable Long id) {
        Optional<ItemDeVenda> itemDeVenda = itemDeVendaRepository.findById(id);
        return itemDeVenda.map(item -> ResponseEntity.ok(itemDeVendaMapper.toDTO(item)))
                          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

 // Endpoint para adicionar um novo item de venda
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ItemDeVendaDTO> adicionarItemDeVenda(@RequestBody @Valid ItemDeVendaDTO itemDeVendaDTO) {
        ItemDeVenda itemDeVenda = itemDeVendaMapper.toEntity(itemDeVendaDTO);
        ItemDeVenda itemSalvo = itemDeVendaRepository.save(itemDeVenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemDeVendaMapper.toDTO(itemSalvo));
    }

    // Endpoint para atualizar um item de venda existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ItemDeVendaDTO> atualizarItemDeVenda(@PathVariable Long id, @RequestBody @Valid ItemDeVendaDTO itemDeVendaDTO) {
        if (itemDeVendaRepository.existsById(id)) {
            ItemDeVenda itemDeVenda = itemDeVendaMapper.toEntity(itemDeVendaDTO);
            itemDeVenda.setId(id); // Atualiza o ID do item de venda
            ItemDeVenda itemAtualizado = itemDeVendaRepository.save(itemDeVenda);
            return ResponseEntity.ok(itemDeVendaMapper.toDTO(itemAtualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

 // Endpoint para excluir um item de venda
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirItemDeVenda(@PathVariable Long id) {
        if (itemDeVendaRepository.existsById(id)) {
            itemDeVendaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
}