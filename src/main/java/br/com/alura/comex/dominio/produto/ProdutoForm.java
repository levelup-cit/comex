package br.com.alura.comex.dominio.produto;

import br.com.alura.comex.dominio.categoria.Categoria;
import br.com.alura.comex.dominio.produto.Produto;
import br.com.alura.comex.dominio.categoria.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoForm {

    @NotNull @NotEmpty @Length(min=2)
    private String nome;

    private String descricao;

    @NotNull @DecimalMin(value = "0")
    private BigDecimal precoUnitario;

    @NotNull
    private Long quantidadeEmEstoque;

    //@NotEmpty
    private Long idCategoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Long getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Long quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Produto converter(CategoriaRepository categoriaRepository) {

        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

        return new Produto(nome, descricao, precoUnitario, quantidadeEmEstoque, categoria.orElseThrow(() -> new IllegalStateException("Anymesage")));
    }
}
