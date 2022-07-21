package br.com.alura.comex.dominio.cliente;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {

  @NotEmpty @NotNull
  private String email;

  @NotEmpty @NotNull
  private String senha;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public UsernamePasswordAuthenticationToken converter(){
    return new UsernamePasswordAuthenticationToken(email, senha);
  }

}
