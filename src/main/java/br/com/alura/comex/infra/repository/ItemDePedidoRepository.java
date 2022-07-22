package br.com.alura.comex.infra.repository;

import br.com.alura.comex.dominio.model.entities.ItemDePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDePedidoRepository extends JpaRepository<ItemDePedido, Long> {
}
