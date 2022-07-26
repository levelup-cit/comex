package br.com.alura.comex.comercial.aplicacao.usuario;

import br.com.alura.comex.comercial.dominio.usuario.Email;
import br.com.alura.comex.comercial.dominio.usuario.Perfil;
import br.com.alura.comex.comercial.dominio.usuario.Usuario;

public class UsuarioBuilder {
    private Usuario usuario = new Usuario();

    public UsuarioBuilder comEmail(Email email) {
        this.usuario.setEmail(email);
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.usuario.setSenha(senha);
        return this;
    }

    public UsuarioBuilder comNome(String nome) {
        this.usuario.setNome(nome);
        return this;
    }

    public UsuarioBuilder addPerfil(Perfil perfil) {
        this.usuario.addPerfil(perfil);
        return this;
    }

    public Usuario build() {
        return this.usuario;
    }

}
