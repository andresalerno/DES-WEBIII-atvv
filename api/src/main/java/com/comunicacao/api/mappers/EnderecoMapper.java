package com.comunicacao.api.mappers;

import com.comunicacao.api.dtos.EnderecoDTO;
import com.comunicacao.api.modelos.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public EnderecoDTO toDTO(Endereco endereco) {
        // Usando o construtor com os parâmetros para criar o DTO
        return new EnderecoDTO(
            endereco.getId(),
            endereco.getTipo(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getBairro(),
            endereco.getCidade(),
            endereco.getEstado(),
            endereco.getCep()
        );
    }

    public Endereco toEntity(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setTipo(dto.getTipo());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
        // Aqui, assumimos que o cliente está sendo passado apenas com o ID (poderia ser feito um novo mapeamento de Cliente)
        return endereco;
    }
}
