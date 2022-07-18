package br.com.alura.comex.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.alura.comex.enums.StatusEnum;

@Entity
@Table(name = "Categoria")
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@Enumerated(EnumType.STRING)
	private StatusEnum status = StatusEnum.ATIVA;

	public CategoriaEntity() {
	}

	public CategoriaEntity(String nome, StatusEnum status) {
		this.nome = nome;
		this.status = status;
	}
	
	public CategoriaEntity(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
