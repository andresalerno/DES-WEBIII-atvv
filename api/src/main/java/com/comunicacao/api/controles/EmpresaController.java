package com.comunicacao.api.controles;


import com.comunicacao.api.dtos.EmpresaDTO;
import com.comunicacao.api.dtos.ServicoMercadoriaDTO;
import com.comunicacao.api.mappers.EmpresaMapper;
import com.comunicacao.api.mappers.ServicoMercadoriaMapper;
import com.comunicacao.api.mock.DataMock;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Empresa;
import com.comunicacao.api.modelos.Funcionario;
import com.comunicacao.api.repositorio.EmpresaRepositorio;
import com.comunicacao.api.repositorio.FuncionarioRepositorio;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "Controle Empresa", description = "Gerencia as empresas do sistema")
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaMapper empresaMapper;
	
    @Autowired
    private EmpresaRepositorio empresaRepository;
    
    @Autowired
    private FuncionarioRepositorio funcionarioRepository;
    
    @Autowired
    private ServicoMercadoriaMapper servicoMapper;

 // Endpoint para listar todas as empresas
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        List<EmpresaDTO> empresaDTOs = empresas.stream()
                                                .map(empresaMapper::toDTO)
                                                .collect(Collectors.toList());
        return ResponseEntity.ok(empresaDTOs);
    }

 // Endpoint para buscar uma empresa por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> buscarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.map(e -> ResponseEntity.ok(empresaMapper.toDTO(e)))
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



 // Endpoint para atualizar uma empresa existente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> atualizarEmpresa(@PathVariable Long id, @RequestBody @Valid EmpresaDTO empresaDTO) {
        if (empresaRepository.existsById(id)) {
            Empresa empresa = empresaMapper.toEntity(empresaDTO);
            empresa.setId(id);
            Empresa empresaAtualizada = empresaRepository.save(empresa);
            EmpresaDTO empresaResposta = empresaMapper.toDTO(empresaAtualizada);
            return ResponseEntity.ok(empresaResposta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


 // Endpoint para excluir uma empresa
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmpresa(@PathVariable Long id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
    
    // Endpoint para listar todos os clientes por empresa (loja)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/clientes")
    public ResponseEntity<List<Cliente>> listarClientesPorEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()) {
            List<Cliente> clientes = empresa.get().getClientes();
            return ResponseEntity.ok(clientes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
     
    @GetMapping("/empresas/{id}/servicos-mercadorias")
    public List<ServicoMercadoriaDTO> listarServicosPorEmpresa(@PathVariable Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow();
        return empresa.getServicosMercadorias().stream()
            .map(servicoMapper::toDTO)
            .collect(Collectors.toList());
    }

}