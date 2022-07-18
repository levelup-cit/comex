package br.com.alura.comex.application.repository;

import br.com.alura.comex.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByNome(String nome);
}
