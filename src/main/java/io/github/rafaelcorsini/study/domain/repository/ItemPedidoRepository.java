package io.github.rafaelcorsini.study.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rafaelcorsini.study.domain.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
