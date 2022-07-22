package br.com.alura.comex.dominio.model.entities;

import br.com.alura.comex.dominio.model.enums.Status;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotBlank(message = "O campo deve ser preenchido")
    @Pattern(regexp = "[A-Za-z ]{2,}+$", message = "O campo deve conter apenas letras e no mínimo 2 caracteres")
    private String nome;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public Categoria(String nome, Status status) {
        this.nome = nome;
        this.status = status;
    }

    public Categoria() {
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

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
