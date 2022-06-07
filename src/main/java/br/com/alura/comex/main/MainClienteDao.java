package br.com.alura.comex.main;

import br.com.alura.comex.dao.ClienteDAO;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.utils.ClienteBuilder;
import br.com.alura.comex.model.utils.EnderecoBuilder;
import br.com.alura.comex.util.JPAUtil;
import br.com.alura.comex.vo.RelatorioQuantidadePedidosPorClienteVo;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MainClienteDao {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        popularBancoDeDados();

        ClienteDAO clienteDAO = new ClienteDAO(em);
        List<Cliente> clientes = clienteDAO.listarPorNome("Chiara");
        clientes.forEach(System.out::println);

        List<RelatorioQuantidadePedidosPorClienteVo> pedidosPorCliente = clienteDAO.getRelatorioPedidosPorCliente();
        pedidosPorCliente.forEach(System.out::println);

    }

    private static void popularBancoDeDados() {
        EntityManager em = JPAUtil.getEntityManager();

        Cliente cliente1 = new ClienteBuilder()
                .comNome("Teresa").comCpf("123456789").comTelefone("11232323")
                .comEndereco(new EnderecoBuilder().comRua("Champs Elysees").comBairro("Arrondissement 1").comNumero("123").comCidade("Paris").comComplemento("Flat").comEstado("Ilha da França").build())
                .comListaDePedidos(new ArrayList<>())
                .build();

        Cliente cliente2 = new ClienteBuilder()
                .comNome("Francisco").comCpf("132456789").comTelefone("12132523")
                .comEndereco(new EnderecoBuilder().comRua("Avenida Paulista").comBairro("Bela Vista").comNumero("111").comCidade("São Paulo").comComplemento("Apartamento").comEstado("Brasil").build())
                .comListaDePedidos(new ArrayList<>())
                .build();

        Cliente cliente3 = new ClienteBuilder()
                .comNome("Chiara").comCpf("142456789").comTelefone("15232523")
                .comEndereco(new EnderecoBuilder().comRua("Ponte Vecchio").comBairro("Cidade Velha").comNumero("S/N").comCidade("Florença").comComplemento("Casa").comEstado("Italia").build())
                .comListaDePedidos(new ArrayList<>())
                .build();

        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();

        clienteDAO.cadastrar(cliente1);
        clienteDAO.cadastrar(cliente2);
        clienteDAO.cadastrar(cliente3);

        em.getTransaction().commit();
        em.close();
    }

}