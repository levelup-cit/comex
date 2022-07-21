package br.com.alura.comex.dominio.cliente;

import br.com.alura.comex.dominio.usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String cpf;

  private String celular;

  @Embedded
  private Endereco endereco;

  @OneToOne
  private Usuario usuario;

  public Cliente() {
  }

  public Cliente(String nome, String cpf,
                 String celular, Endereco endereco, Usuario usuario) {
    this.nome = nome;
    this.cpf = cpf;
    this.celular = celular;
    this.endereco = endereco;
    this.usuario = usuario;
  }

  @Override
  public String toString() {
    return "Cliente{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", cpf='" + cpf + '\'' +
            ", celular='" + celular + '\'' +
            ", endereco=" + endereco +
            ", usuario=" + usuario +
            '}';
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public String getCelular() {
    return celular;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public Usuario getUsuario() {
    return usuario;
  }
}
