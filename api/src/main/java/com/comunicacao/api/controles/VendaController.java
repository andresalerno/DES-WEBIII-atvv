package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.VendaDTO;
import com.comunicacao.api.mappers.VendaMapper;
import com.comunicacao.api.modelos.Venda;
import com.comunicacao.api.repositorio.VendaRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Controle Venda", description = "Gerencia as vendas do sistema")
@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepositorio vendaRepository;
    
    @Autowired
    private VendaMapper vendaMapper;

    // Endpoint para listar todas as vendas
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        List<VendaDTO> dtos = vendas.stream()
                .map(vendaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }


    // Endpoint para buscar uma venda por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVenda(@PathVariable Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar uma nova venda
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Venda> adicionarVenda(@RequestBody @Valid Venda venda) {
        Venda vendaSalva = vendaRepository.save(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
    }

    // Endpoint para atualizar uma venda existente
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable Long id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    @Tag(name = "🚨 Endpoints Críticos")
    @Operation(
        summary = "🚨 [TAREFA] 4.Listar vendas por empresa e período",
        description = "⚠️ Retorna todas as vendas de uma empresa em um intervalo de datas (formato: yyyy-MM-dd)"
    )
    @GetMapping("/empresa/{empresaId}/periodo")
    public ResponseEntity<List<VendaDTO>> listarVendasPorEmpresaEPeriodo(
            @PathVariable Long empresaId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        List<Venda> vendas = vendaRepository
            .findByServicoMercadoria_Empresa_IdAndDataVendaBetween(empresaId, startDate, endDate);

        if (vendas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }

        List<VendaDTO> dtoList = vendas.stream()
            .map(vendaMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}
