package br.com.alura.comex.controller.form;
/*
 * DTO -> dados que saem da API de volta pro cliente; Form os dados que chegam do cliente para a API.
 */

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.modelo.CategoriaEntity;
import br.com.alura.comex.repository.CategoriaRepository;

public class CategoriaForm {

	@NotNull @NotEmpty @Length(min = 2)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaEntity converter() {
		return new CategoriaEntity(this.nome);
	}

	public CategoriaEntity atualizar(Long id, CategoriaRepository categoriaRepository) {
		CategoriaEntity categoria = categoriaRepository.getReferenceById(id);
		categoria.setNome(this.nome);
		
		return categoria;
	}

}
