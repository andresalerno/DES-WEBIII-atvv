package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.ItemDeVendaDTO;
import com.comunicacao.api.mappers.ItemDeVendaMapper;
import com.comunicacao.api.modelos.ItemDeVenda;
import com.comunicacao.api.repositorio.ItemDeVendaRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Controle Itens de Venda", description = "Gerencia os itens de venda")
@RestController
@RequestMapping("/itens-venda")
public class ItemDeVendaController {

    @Autowired
    private ItemDeVendaRepositorio itemDeVendaRepo;

    @Autowired
    private ItemDeVendaMapper itemDeVendaMapper;

    @GetMapping
    public List<ItemDeVendaDTO> listarTodos() {
        return itemDeVendaRepo.findAll().stream()
                .map(itemDeVendaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDeVendaDTO> buscarPorId(@PathVariable Long id) {
        Optional<ItemDeVenda> item = itemDeVendaRepo.findById(id);
        return item.map(i -> ResponseEntity.ok(itemDeVendaMapper.toDTO(i)))
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/venda/{vendaId}")
    public List<ItemDeVendaDTO> buscarPorVenda(@PathVariable Long vendaId) {
        return itemDeVendaRepo.findByVenda_Id(vendaId).stream()
                .map(itemDeVendaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ItemDeVendaDTO> criar(@RequestBody ItemDeVendaDTO dto) {
        ItemDeVenda item = itemDeVendaMapper.toEntity(dto);
        item = itemDeVendaRepo.save(item);
        return ResponseEntity.ok(itemDeVendaMapper.toDTO(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDeVendaDTO> atualizar(@PathVariable Long id, @RequestBody ItemDeVendaDTO dto) {
        if (!itemDeVendaRepo.existsById(id)) return ResponseEntity.notFound().build();

        ItemDeVenda item = itemDeVendaMapper.toEntity(dto);
        item.setId(id);
        item = itemDeVendaRepo.save(item);
        return ResponseEntity.ok(itemDeVendaMapper.toDTO(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!itemDeVendaRepo.existsById(id)) return ResponseEntity.notFound().build();

        itemDeVendaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
