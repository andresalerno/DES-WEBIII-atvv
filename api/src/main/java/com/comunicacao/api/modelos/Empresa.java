package com.comunicacao.api.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cnpj;

    private String enderecoPrincipal;
    
 // Relacionamento bidirecional com Cliente
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cliente> clientes;
    // Relacionamento com funcionários, clientes, etc.

 // Relacionamento com ServicoMercadoria
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ServicoMercadoria> servicosMercadorias;  // Relacionamento com serviços e mercadorias
    
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Veiculo> veiculos;  // Relacionamento com veículos da empresa
}

