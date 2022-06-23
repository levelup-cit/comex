package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Cliente;
import org.springframework.data.domain.Page;

public class ResumoClienteDto {
    private String nome;
    private Long cpf;
    private String telefone;
    private String local;

    public ResumoClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.local = cliente.getEndereco().getCidade() + "/" + cliente.getEndereco().getEstado();
    }

    public static Page<ResumoClienteDto> converter(Page<Cliente> clientes) {
        return clientes.map(ResumoClienteDto::new);
    }
}
