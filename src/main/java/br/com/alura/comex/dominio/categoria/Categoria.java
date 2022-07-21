package br.com.alura.comex.dominio.categoria;

import javax.persistence.*;

@Entity
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Enumerated(EnumType.STRING)
  private StatusDaCategoria status;

  public Categoria() { }

  public Categoria(String nome) {
    this.nome = nome;
    this.status = StatusDaCategoria.ATIVO;
  }

  @Override
  public String toString() {
    return "Categoria [nome=" + nome + ", status=" + status +  "]";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public StatusDaCategoria getStatus() {
    return status;
  }

  public void setStatus(StatusDaCategoria status) {
    this.status = status;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void alteraStatus(Categoria categoria){
    if(categoria.getStatus().equals(StatusDaCategoria.ATIVO)){
      categoria.setStatus(StatusDaCategoria.INATIVO);
    } else{
      categoria.setStatus(StatusDaCategoria.ATIVO);
    }
  }

}
