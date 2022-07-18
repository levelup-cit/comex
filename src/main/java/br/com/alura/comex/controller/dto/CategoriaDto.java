package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.alura.comex.modelo.CategoriaEntity;

public class CategoriaDto {

	private String nome;

	public CategoriaDto(CategoriaEntity categoria) {
		this.nome = categoria.getNome();
	}

	public String getNome() {
		return nome;
	}

	public static Page<CategoriaDto> converter(Page<CategoriaEntity> categorias) {
		return categorias.map(CategoriaDto::new);
	}
}
