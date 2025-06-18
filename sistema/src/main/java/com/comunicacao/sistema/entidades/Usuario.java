package com.comunicacao.sistema.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.comunicacao.sistema.enumeracoes.TipoUsuario;

import lombok.Data;

@Data
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome;
	
	@Column
	private String password;
	
	@Column
    private String username;
	
	// Agora o usuário tem um tipo de usuário (role)
    @Column
    private TipoUsuario tipoUsuario;  // TipoUsuario é o ENUM que você tem (ROLE_ADMIN, ROLE_CLIENTE, etc.)

    // Método para acessar o tipo de usuário
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}