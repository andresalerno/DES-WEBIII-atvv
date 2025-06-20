package com.comunicacao.api.controles;

import com.comunicacao.api.dtos.VeiculoDTO;
import com.comunicacao.api.mappers.VeiculoMapper;
import com.comunicacao.api.modelos.Empresa;
import com.comunicacao.api.modelos.Veiculo;
import com.comunicacao.api.repositorio.EmpresaRepositorio;
import com.comunicacao.api.repositorio.VeiculoRepositorio;

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

@Tag(name = "Controle Veículo", description = "Gerencia os veículos do sistema")
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private EmpresaRepositorio empresaRepository;

    @Autowired
    private VeiculoRepositorio veiculoRepository;

    @Autowired
    private VeiculoMapper veiculoMapper;

    // Endpoint para listar todos os veículos
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<VeiculoDTO> listarVeiculos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream()
                     .map(veiculoMapper::toDTO)
                     .collect(Collectors.toList());
    }

    // Endpoint para buscar um veículo por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarVeiculo(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        return veiculo.map(v -> ResponseEntity.ok(veiculoMapper.toDTO(v)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para adicionar um novo veículo
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<VeiculoDTO> adicionarVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO) {
        // Buscar a empresa associada
        Empresa empresa = empresaRepository.findById(veiculoDTO.getEmpresaId()).orElse(null);
        if (empresa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Empresa não encontrada
        }

        // Mapeia o DTO para entidade
        Veiculo veiculo = veiculoMapper.toEntity(veiculoDTO);
        veiculo.setEmpresa(empresa);  // Atribui a empresa ao veículo

        // Salva o veículo
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        // Adiciona o veículo à lista de veículos da empresa
        empresa.getVeiculos().add(veiculoSalvo);
        empresaRepository.save(empresa);

        // Retorna o DTO do veículo salvo
        VeiculoDTO veiculoResposta = veiculoMapper.toDTO(veiculoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoResposta);
    }



    // Endpoint para atualizar um veículo existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody @Valid VeiculoDTO veiculoDTO) {
        if (veiculoRepository.existsById(id)) {
            Veiculo veiculo = veiculoMapper.toEntity(veiculoDTO);
            veiculo.setId(id);  // Atualiza o ID do veículo
            Veiculo veiculoAtualizado = veiculoRepository.save(veiculo);
            return ResponseEntity.ok(veiculoMapper.toDTO(veiculoAtualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para excluir um veículo
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();  // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna 404 Not Found
        }
    }

    // Endpoint para listar todos os veículos de uma empresa
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<VeiculoDTO>> listarVeiculosPorEmpresa(@PathVariable Long empresaId) {
        List<Veiculo> veiculos = veiculoRepository.findByEmpresaId(empresaId);
        if (veiculos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Se não encontrar, retorna 404
        } else {
            List<VeiculoDTO> veiculosDTO = veiculos.stream()
                                                   .map(veiculoMapper::toDTO)
                                                   .collect(Collectors.toList());
            return ResponseEntity.ok(veiculosDTO);  // Retorna a lista de veículos com status 200
        }
    }
}
