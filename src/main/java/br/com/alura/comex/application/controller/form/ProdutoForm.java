package br.com.alura.comex.application.controller.form;

import br.com.alura.comex.domain.Categoria;
import br.com.alura.comex.domain.Dimensoes;
import br.com.alura.comex.domain.Produto;
import br.com.alura.comex.application.repository.CategoriaRepository;
import br.com.alura.comex.domain.factory.DimensoesBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoForm {
    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    private String descricao;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoUnitario;

    @NotNull
    private int quantidadeEstoque;

    @NotNull
    private Long categoria;

    @NotNull
    private Double altura;

    @NotNull
    private Double comprimento;

    @NotNull
    private Double peso;

    @NotNull
    private Double largura;

    public Produto converter(CategoriaRepository categoriaRepository) {
        Optional<Categoria> novaCategoria = categoriaRepository.findById(categoria);
        Dimensoes dimensoes = new DimensoesBuilder().comAltura(altura).comComprimento(comprimento).comPeso(peso).comLargura(largura).build();
        return new Produto(nome, descricao, precoUnitario, quantidadeEstoque, novaCategoria.get(), dimensoes);
    }
}
