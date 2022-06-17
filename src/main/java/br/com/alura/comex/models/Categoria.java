package br.com.alura.comex.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Categoria() {

  }

  @Column(nullable = false, unique = true)
  private String nome;

  @Column(nullable = false)
  private boolean ativo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void alteraAtivacao() {
    this.ativo = !this.ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public void ativaCategoria() {
    this.ativo = true;
  }

  @Override
  public String toString() {
    return "Categoria{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", ativo=" + ativo +
        '}';
  }

}
