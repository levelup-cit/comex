package br.com.alura.comex.dominio.cliente;

import br.com.alura.comex.dominio.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
