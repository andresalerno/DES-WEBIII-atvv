package com.comunicacao.api.controles;

import com.comunicacao.api.modelos.Veiculo;
import com.comunicacao.api.repositorio.VeiculoRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Controle Veículo", description = "Gerencia os veículos do sistema")
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepositorio veiculoRepository;

    // Endpoint para listar todos os veículos
    @GetMapping
    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    // Endpoint para buscar um veículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarVeiculo(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo veículo
    @PostMapping
    public ResponseEntity<Veiculo> adicionarVeiculo(@RequestBody @Valid Veiculo veiculo) {
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }

    // Endpoint para atualizar um veículo existente
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody @Valid Veiculo veiculo) {
        if (veiculoRepository.existsById(id)) {
            veiculo.setId(id); // Atualiza o ID do veículo
            Veiculo veiculoAtualizado = veiculoRepository.save(veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um veículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
}
