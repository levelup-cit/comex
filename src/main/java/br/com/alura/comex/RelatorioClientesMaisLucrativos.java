package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioClientesMaisLucrativos {
	
<<<<<<< HEAD
	Map<String, List<Pedido>>clienteLucro;
	Map<String, BigDecimal> montanteCliente;
=======
	private Map<String, List<Pedido>>clienteLucro;
	private Map<String, BigDecimal> montanteCliente;
>>>>>>> 6a982afa247369bab4370a880d5352a6b0039df2
	
	
	
	
	
	public RelatorioClientesMaisLucrativos(List<Pedido> listaDeLucrativos) {
		super();
    	if(listaDeLucrativos == null || listaDeLucrativos.isEmpty()) throw new IllegalArgumentException("A lista não pode ser nula.");
<<<<<<< HEAD
		agrupandoClientesMaisLucrativos(listaDeLucrativos);
		montanteDeClientesMaisLucrativos(listaDeLucrativos);

	}

	private void montanteDeClientesMaisLucrativos(List<Pedido> listaDeLucrativos) {
		this.montanteCliente = new TreeMap<>();
		listaDeLucrativos.stream()
						 .collect(Collectors.groupingBy(Pedido::getCliente))
						 .forEach((x,y) -> montanteCliente.put(x, y.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add)));
	}

	private void agrupandoClientesMaisLucrativos(List<Pedido> listaDeLucrativos) {
		this.clienteLucro = new TreeMap();
		listaDeLucrativos.stream()
						.collect(Collectors.groupingBy(Pedido::getCliente)).forEach((x, y) -> clienteLucro.put(x, y));
	}

	public static void ImprimirRelatorioDeClientesMaisLucrativos(RelatorioClientesMaisLucrativos lucrativo) {
		System.out.println("####RELATORIO CLIENTE MAIS LUCRATIVO");
		lucrativo.getMontanteCliente()
				.entrySet().stream()
				.sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
				.limit(2)
				.forEach(x -> {
					System.out.printf("\nCLIENTE: %s\nMONTANTE: %s\nN°de Pedidos %s\n", x.getKey(), x.getValue(),lucrativo.getClienteLucro().get(x.getKey()));

				});
	}
=======
    	this.clienteLucro = new TreeMap();
        listaDeLucrativos.stream()
                		.collect(Collectors.groupingBy(Pedido::getCliente)).forEach((x, y) -> clienteLucro.put(x, y));
    	this.montanteCliente = new TreeMap<>();
        listaDeLucrativos.stream()
                         .collect(Collectors.groupingBy(Pedido::getCliente))
                         .forEach((x,y) -> montanteCliente.put(x, y.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add)));

    	}


>>>>>>> 6a982afa247369bab4370a880d5352a6b0039df2



	public Map<String, List<Pedido>> getClienteLucro() {
		return clienteLucro;
	}





	public Map<String, BigDecimal> getMontanteCliente() {
		return montanteCliente;
	}
	
	
    
	

}