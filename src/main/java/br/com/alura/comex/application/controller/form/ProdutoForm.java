package br.com.alura.comex.application.controller.form;

import br.com.alura.comex.domain.Categoria;
import br.com.alura.comex.domain.Produto;
import br.com.alura.comex.application.repository.CategoriaRepository;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Optional;

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

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Long getCategoria() {
        return categoria;
    }

    public Produto converter(CategoriaRepository categoriaRepository) {
        Optional<Categoria> novaCategoria = categoriaRepository.findById(categoria);
        return new Produto(nome, descricao, precoUnitario, quantidadeEstoque, novaCategoria.get());
    }

    @Override
    public String toString() {
        return "ProdutoForm{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}