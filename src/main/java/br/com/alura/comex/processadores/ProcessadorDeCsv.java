package br.com.alura.comex.processadores;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.alura.comex.pedido.Pedido;

public class ProcessadorDeCsv {

	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

	public ArrayList<Pedido> registrarPedidos() {
		try {

			URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
			Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

			Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

			leitorDeLinhas.nextLine();

			this.registrarPedidos(leitorDeLinhas);
			
			leitorDeLinhas.close();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Arquivo pedido.csv não localizado!");
		} catch (IOException e) {
			throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
		}

		return this.pedidos;
		
	}

	private void registrarPedidos(Scanner leitorDeLinhas) {
		int quantidadeDeRegistros = 0;
		while (leitorDeLinhas.hasNextLine()) {
			String linha = leitorDeLinhas.nextLine();
			String[] registro = linha.split(",");

			String categoria = registro[0];
			String produto = registro[1];
			BigDecimal preco = new BigDecimal(registro[2]);
			int quantidade = Integer.parseInt(registro[3]);
			LocalDate data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String cliente = registro[5];

			Pedido pedido = new Pedido(categoria, produto, cliente, preco, quantidade, data);
			this.pedidos.add(pedido);

			quantidadeDeRegistros++;
		}
	}
}