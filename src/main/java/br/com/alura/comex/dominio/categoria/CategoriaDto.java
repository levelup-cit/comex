package br.com.alura.comex.dominio.categoria;

import java.util.List;

public class CategoriaDto {

  private Long id;

  private String nome;

  private StatusDaCategoria status;

  public CategoriaDto(Categoria categoria) {
    this.id = categoria.getId();
    this.nome = categoria.getNome();
    this.status = categoria.getStatus();
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public StatusDaCategoria getStatus() {
    return status;
  }

  public static List<CategoriaDto> converter(List<Categoria> categorias) {
    return categorias.stream().map(CategoriaDto::new).toList();
  }
}
