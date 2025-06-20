package com.comunicacao.sistema.controles;

import com.comunicacao.sistema.jwt.GeradorJwt;
import com.comunicacao.sistema.entidades.Usuario;
import com.comunicacao.sistema.enumeracoes.TipoUsuario;
import com.comunicacao.sistema.repositorios.RepositorioUsuario;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controle Autenticação", description = "Gerencia o processo de autenticação e geração de tokens JWT para usuários")
@RestController
@RequestMapping("/auth")
public class ControleUsuario {

    @Autowired
    private RepositorioUsuario repositorio;

    @Autowired
    private GeradorJwt geradorJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Endpoint para retornar todos os usuários (protegido)
    @GetMapping("/usuarios")
    public ResponseEntity<?> obterUsuarios() {
        List<Usuario> usuarios = repositorio.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Endpoint de login para gerar o JWT
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/login") // Esse mapeamento é para o POST de login
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        // Tenta autenticar o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Verifica se o usuário existe na base de dados
        Optional<Usuario> usuario = repositorio.findByUsername(username);

        // Se o usuário não for encontrado, retorna uma resposta de erro
        if (!usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }

        // Converte o tipoUsuario para uma String
        TipoUsuario tipoUsuario = usuario.get().getTipoUsuario();

        // Se o usuário for encontrado, gera o token JWT
        String token = geradorJwt.gerarToken(username, tipoUsuario);

        // Retorna o token JWT na resposta
        return ResponseEntity.ok().body("Bearer " + token);
    }
}
