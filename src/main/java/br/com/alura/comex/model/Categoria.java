package br.com.alura.comex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

//    @Enumerated(EnumType.STRING)
//    private StatusDaCategoria status;

    public Categoria(){}

    public Categoria(String nome){
        this.nome = nome;
        //this.status = StatusDaCategoria.ATIVO;
    }

//    @Override
//    public String toString() {
//        return "Categoria [nome=" + nome + ", status=" + status +  "]";
//    }

    public String getNome() {
        return nome;
    }

//    public StatusDaCategoria getStatus() {
//        return status;
//    }

//    public void setStatus(StatusDaCategoria status) {
//        this.status = status;
//    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
