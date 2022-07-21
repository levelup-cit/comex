package br.com.alura.comex.compartilhado.entity.cliente;

import lombok.*;

import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Endereco {

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    public String getEnderecoCompleto() {
        return new StringJoiner(", ")
                .add(this.rua)
                .add(this.numero)
                .add(this.bairro)
                .add(this.cidade)
                .add(this.complemento)
                .toString();

    }
}
