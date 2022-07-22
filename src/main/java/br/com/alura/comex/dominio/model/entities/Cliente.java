package br.com.alura.comex.dominio.model.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotBlank(message = "O campo deve ser preenchido")
    @Pattern(regexp = "/^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\s]+$/", message = "O campo deve conter apenas letras")
    private String nome;
    @NotBlank(message = "O campo deve ser preenchido")
    @Pattern(regexp = "\\d+$", message = "O campo deve conter apenas números")
    private String cpf;
    @NotBlank(message = "O campo deve ser preenchido")
    @Pattern(regexp = "\\d+$", message = "O campo deve conter apenas números")
    private String telefone;
    @NotEmpty
    private String rua;
    @NotBlank(message = "O campo deve ser preenchido")
    @Pattern(regexp = "\\d+$", message = "O campo deve conter apenas números")
    private String numero;
    @NotEmpty
    private String complemento;
    @NotEmpty
    private String bairro;
    @NotBlank(message = "O campo deve ser preenchido")
    @Pattern(regexp = "/^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\s]+$/", message = "O campo deve conter apenas letras")
    private String cidade;
    @NotBlank(message = "O campo deve ser preenchido")
    @Pattern(regexp = "/^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\s]+$/", message = "O campo deve conter apenas letras")
    private String estado;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> listaDePedidos;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pedido> getListaDePedidos() {
        return listaDePedidos;
    }

    public void setListaDePedidos(List<Pedido> listaDePedidos) {
        this.listaDePedidos = listaDePedidos;
    }

    public Cliente(String nome, String cpf, String telefone, String rua, String numero, String complemento, String bairro, String cidade, String estado, List<Pedido> listaDePedidos) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.listaDePedidos = listaDePedidos;
    }

    public Cliente() {
    }
}
