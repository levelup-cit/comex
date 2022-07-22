package br.com.alura.comex.a.service.impl;

import br.com.alura.comex.a.service.ClienteService;
import br.com.alura.comex.dominio.model.entities.Cliente;
import br.com.alura.comex.infra.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodos() {

        return clienteRepository.findAll();
    }

    @Override
    public Page<Cliente> obterPagina(Integer page, Integer linhasPorPage, String ordenarPor, String direcao){
        PageRequest pageRequest = PageRequest.of(page,linhasPorPage, Sort.Direction.valueOf(direcao), ordenarPor);
        return clienteRepository.findAll(pageRequest);
    }

    @Override
    public Cliente listarPorCodigo(Long id) {

        return clienteRepository.findById(id).get();
    }

    @Override
    public Cliente inserir(Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {

        return this.clienteRepository.save(cliente);
    }

    @Override
    public void remover(Long id) {

        this.clienteRepository.deleteById(id);
    }
}
