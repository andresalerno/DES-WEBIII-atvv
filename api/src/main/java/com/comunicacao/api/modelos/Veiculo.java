package com.comunicacao.api.modelos;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private String marca;

    private String placa;

    @ManyToOne
    @JoinColumn(name = "empresa_id") // Nome da coluna de chave estrangeira
    private Empresa empresa;  // Relacionamento com a empresa (loja)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
