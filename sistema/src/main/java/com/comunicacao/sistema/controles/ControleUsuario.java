package com.comunicacao.sistema.controles;

import com.comunicacao.sistema.jwt.GeradorJwt;
import com.comunicacao.sistema.entidades.Usuario;
import com.comunicacao.sistema.repositorios.RepositorioUsuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
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
        return new ResponseEntity<>(usuarios, HttpStatus.FOUND);
    }

    // Endpoint de login para gerar o JWT
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        // Autenticação com o Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Depois de autenticar, gera o token JWT
        String role = "USER"; // A role pode ser definida dinamicamente com base nas permissões do usuário
        String token = geradorJwt.gerarToken(username, role);

        // Retorna o token JWT para o cliente
        return ResponseEntity.ok().body("Bearer " + token);
    }
}
