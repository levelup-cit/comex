package br.com.alura.comex;

import java.util.ArrayList;
import java.util.List;

public class CategoriasProcessadas {

  ArrayList<String> categorias;

  public CategoriasProcessadas(List<Pedido> pedidos) {
    this.categorias = new ArrayList<>();
    this.verificaSeEstaNaCategoriaEAdiciona(pedidos);
  }

  public List<String> getSortedCategorias() {
    return this.categorias.stream().sorted((a, b) -> a.compareTo(b)).toList();
  }

  public ArrayList<String> getCategorias() {
    return this.categorias;
  }

  public boolean contains(String categoria) {
    return categorias.contains(categoria);
  }

  private void add(String categoria) {
    categorias.add(categoria);
  }

  public int getTotalDeCategorias() {
    return categorias.size();
  }

  public void verificaSeEstaNaCategoriaEAdiciona(List<Pedido> pedidos) {
    pedidos
      .stream()
      .forEach(
        pedido -> {
          if (!categorias.contains(pedido.getProduto().getCategoria())) {
            this.add(pedido.getProduto().getCategoria());
          }
        }
      );
  }
}
