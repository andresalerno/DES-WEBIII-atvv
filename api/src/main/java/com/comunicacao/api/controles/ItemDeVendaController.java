package com.comunicacao.api.controles;

import com.comunicacao.api.modelos.ItemDeVenda;
import com.comunicacao.api.repositorio.ItemDeVendaRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Controle Itens de Venda", description = "Gerencia os itens de venda do sistema")
@RestController
@RequestMapping("/itens-venda")
public class ItemDeVendaController {

    @Autowired
    private ItemDeVendaRepositorio itemDeVendaRepository;

    // Endpoint para listar todos os itens de venda
    @GetMapping
    public List<ItemDeVenda> listarItensDeVenda() {
        return itemDeVendaRepository.findAll();
    }

    // Endpoint para buscar um item de venda por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemDeVenda> buscarItemDeVenda(@PathVariable Long id) {
        Optional<ItemDeVenda> itemDeVenda = itemDeVendaRepository.findById(id);
        return itemDeVenda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo item de venda
    @PostMapping
    public ResponseEntity<ItemDeVenda> adicionarItemDeVenda(@RequestBody @Valid ItemDeVenda itemDeVenda) {
        ItemDeVenda itemSalvo = itemDeVendaRepository.save(itemDeVenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemSalvo);
    }

    // Endpoint para atualizar um item de venda existente
    @PutMapping("/{id}")
    public ResponseEntity<ItemDeVenda> atualizarItemDeVenda(@PathVariable Long id, @RequestBody @Valid ItemDeVenda itemDeVenda) {
        if (itemDeVendaRepository.existsById(id)) {
            itemDeVenda.setId(id); // Atualiza o ID do item de venda
            ItemDeVenda itemAtualizado = itemDeVendaRepository.save(itemDeVenda);
            return ResponseEntity.ok(itemAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um item de venda
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
