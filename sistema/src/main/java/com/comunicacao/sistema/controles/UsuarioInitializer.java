package com.comunicacao.sistema.controles;

import com.comunicacao.sistema.entidades.Usuario;
import com.comunicacao.sistema.enumeracoes.TipoUsuario;
import com.comunicacao.sistema.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UsuarioInitializer {

    @Autowired
    private RepositorioUsuario repositorio;

    @PostConstruct
    public void init() {
        // Criar usuário admin caso não exista
        if (repositorio.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword("admin123");  // Lembre-se de criptografar a senha se usar em produção
            admin.setTipoUsuario(TipoUsuario.ROLE_ADMIN);
            repositorio.save(admin);
        }
    }
}
