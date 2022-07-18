package br.com.alura.comex.application.controller.dto;

import br.com.alura.comex.domain.Cliente;
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

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLocal() {
        return local;
    }


    public static Page<ResumoClienteDto> converter(Page<Cliente> clientes) {
        return clientes.map(ResumoClienteDto::new);
    }
}
