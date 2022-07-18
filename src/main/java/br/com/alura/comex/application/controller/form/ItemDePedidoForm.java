package br.com.alura.comex.application.controller.form;

import br.com.alura.comex.domain.ItemDePedido;
import br.com.alura.comex.domain.Produto;
import br.com.alura.comex.application.repository.ProdutoRepository;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ItemDePedidoForm {
    @NotNull
    @Min(0)
    @Valid
    private Long idProduto;

    @Min(1)
    private int quantidadeProduto;

    public ItemDePedidoForm() {}

    private Produto verificarProduto(ProdutoRepository produtoRepository){
        Optional<Produto> produto = produtoRepository.findById(this.idProduto);
        if (produto.get().getQuantidadeEstoque() < this.quantidadeProduto){
            throw new RuntimeException("Sem produto em estoque");
        }

        produto.get().setQuantidadeEstoque(
                produto.get().getQuantidadeEstoque() - this.quantidadeProduto
        );
        return produto.get();
    }

    public ItemDePedido converter(ProdutoRepository produtoRepository){
        return new ItemDePedido(this.quantidadeProduto, verificarProduto(produtoRepository));
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    @Override
    public String toString() {
        return "ItemDePedidoForm{" +
                "idProduto=" + idProduto +
                ", quantidadeProduto=" + quantidadeProduto +
                '}';
    }
}
