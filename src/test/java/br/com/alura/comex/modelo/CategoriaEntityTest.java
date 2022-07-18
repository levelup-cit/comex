package br.com.alura.comex.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.alura.comex.enums.StatusEnum;

class CategoriaEntityTest {

	@Test
	void deveriaEncontrarPorNome() {
		CategoriaEntity categoria = new CategoriaEntity("Nome categoria teste");
		assertEquals("Nome categoria teste", categoria.getNome());
	}

	@Test
	void deveriaEncontrarPorNomeEStatus() {
		CategoriaEntity categoria = new CategoriaEntity("Nome categoria teste 2", StatusEnum.ATIVA);
		assertEquals("Nome categoria teste 2", categoria.getNome());
		assertEquals(StatusEnum.ATIVA, categoria.getStatus());
	}

}
