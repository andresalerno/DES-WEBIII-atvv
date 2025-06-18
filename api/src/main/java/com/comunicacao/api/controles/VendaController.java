package com.comunicacao.api.controles;

import com.comunicacao.api.modelos.Venda;
import com.comunicacao.api.repositorio.VendaRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Controle Venda", description = "Gerencia as vendas do sistema")
@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepositorio vendaRepository;

    // Endpoint para listar todas as vendas
    @GetMapping
    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

    // Endpoint para buscar uma venda por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVenda(@PathVariable Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar uma nova venda
    @PostMapping
    public ResponseEntity<Venda> adicionarVenda(@RequestBody @Valid Venda venda) {
        Venda vendaSalva = vendaRepository.save(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
    }

    // Endpoint para atualizar uma venda existente
    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizarVenda(@PathVariable Long id, @RequestBody @Valid Venda venda) {
        if (vendaRepository.existsById(id)) {
            venda.setId(id); // Atualiza o ID da venda
            Venda vendaAtualizada = vendaRepository.save(venda);
            return ResponseEntity.ok(vendaAtualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir uma venda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable Long id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
}
