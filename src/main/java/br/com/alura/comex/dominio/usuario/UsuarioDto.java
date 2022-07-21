package br.com.alura.comex.dominio.usuario;

import java.util.List;

public class UsuarioDto {

  private Long id;

  private String email;

  private String senha;

  private String perfil;

  public UsuarioDto(Usuario usuario) {
    this.id = usuario.getId();
    this.email = usuario.getEmail();
    this.senha = usuario.getSenha();
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getSenha() {
    return senha;
  }

  public String getPerfil() {
    return perfil;
  }

  public static List<UsuarioDto> converter(List<Usuario> usuarios) {
    return usuarios.stream().map(UsuarioDto::new).toList();
  }
}
