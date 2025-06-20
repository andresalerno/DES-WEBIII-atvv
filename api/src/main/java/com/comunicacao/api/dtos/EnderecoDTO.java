package com.comunicacao.api.dtos;

import com.comunicacao.api.enumeracoes.TipoEndereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoDTO {

    private Long id;
    private TipoEndereco tipo;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private Long clienteId;

    // Construtor com os parâmetros necessários para o mapeamento
    public EnderecoDTO(Long id, TipoEndereco tipo, String logradouro, String numero, String bairro, String cidade, String estado, String cep) {
        this.id = id;
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
}
