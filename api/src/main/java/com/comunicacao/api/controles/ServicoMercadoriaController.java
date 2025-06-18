package com.comunicacao.api.controles;

import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.repositorio.ServicoMercadoriaRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Controle Serviço / Mercadoria", description = "Gerencia os serviços e mercadorias do sistema")
@RestController
@RequestMapping("/servicos-mercadorias")
public class ServicoMercadoriaController {

    @Autowired
    private ServicoMercadoriaRepositorio servicoMercadoriaRepository;

    // Endpoint para listar todos os serviços/mercadorias
    @GetMapping
    public List<ServicoMercadoria> listarServicosMercadorias() {
        return servicoMercadoriaRepository.findAll();
    }

    // Endpoint para buscar um serviço/mercadoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicoMercadoria> buscarServicoMercadoria(@PathVariable Long id) {
        Optional<ServicoMercadoria> servicoMercadoria = servicoMercadoriaRepository.findById(id);
        return servicoMercadoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo serviço/mercadoria
    @PostMapping
    public ResponseEntity<ServicoMercadoria> adicionarServicoMercadoria(@RequestBody @Valid ServicoMercadoria servicoMercadoria) {
        ServicoMercadoria servicoMercadoriaSalvo = servicoMercadoriaRepository.save(servicoMercadoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoMercadoriaSalvo);
    }

    // Endpoint para atualizar um serviço/mercadoria existente
    @PutMapping("/{id}")
    public ResponseEntity<ServicoMercadoria> atualizarServicoMercadoria(@PathVariable Long id, @RequestBody @Valid ServicoMercadoria servicoMercadoria) {
        if (servicoMercadoriaRepository.existsById(id)) {
            servicoMercadoria.setId(id); // Atualiza o ID do serviço/mercadoria
            ServicoMercadoria servicoMercadoriaAtualizado = servicoMercadoriaRepository.save(servicoMercadoria);
            return ResponseEntity.ok(servicoMercadoriaAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um serviço/mercadoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServicoMercadoria(@PathVariable Long id) {
        if (servicoMercadoriaRepository.existsById(id)) {
            servicoMercadoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
}