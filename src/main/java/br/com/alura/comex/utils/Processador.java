package br.com.alura.comex.utils;

import java.util.List;

import br.com.alura.comex.model.Pedido;

public interface Processador {
	
	static final String arquivoCSV = "";

	abstract List<Pedido> lerRegistros() throws Exception;
}