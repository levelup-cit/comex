package br.com.alura.comex.application.repository;

import br.com.alura.comex.domain.ItemDePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDePedidoRepository extends JpaRepository<ItemDePedido, Long> {

}
