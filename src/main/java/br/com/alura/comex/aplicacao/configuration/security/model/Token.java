package br.com.alura.comex.aplicacao.security.model;


public class Token {

    private String token;
    private String tipoAutenticacao;

    public Token(String token, String tipoAutenticacao) {
        this.token = token;
        this.tipoAutenticacao = tipoAutenticacao;
    }

    public String getToken() {
        return token;
    }

    public String getTipoAutenticacao() {
        return tipoAutenticacao;
    }
}
