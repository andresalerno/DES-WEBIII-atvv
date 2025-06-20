package com.comunicacao.api.dtos;

import java.util.List;

import lombok.Data;

@Data
public class FuncionarioDTO {

    private Long id;
    private String nome;
    private String perfil;
    private List<DocumentoDTO> documentos;
    private List<TelefoneDTO> telefones;
    private List<EnderecoDTO> enderecos;
    private Long empresaId; // Apenas o ID da empresa

    
}
