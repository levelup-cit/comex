package br.com.alura.comex.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Dimensoes {
    private Double comprimento;
    private Double altura;
    private Double largura;
    private Double peso;
}