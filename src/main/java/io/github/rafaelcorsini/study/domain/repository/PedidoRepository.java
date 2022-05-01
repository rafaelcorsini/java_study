package io.github.rafaelcorsini.study.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.rafaelcorsini.study.domain.entity.Cliente;
import io.github.rafaelcorsini.study.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByCliente(Cliente cliente);
	
	@Query("select p from Pedido p left join fetch p.items where p.id = :id")
	Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
