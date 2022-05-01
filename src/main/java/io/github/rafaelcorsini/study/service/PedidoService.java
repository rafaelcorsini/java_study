package io.github.rafaelcorsini.study.service;

import java.util.Optional;

import io.github.rafaelcorsini.study.domain.entity.Pedido;
import io.github.rafaelcorsini.study.domain.enums.StatusPedido;
import io.github.rafaelcorsini.study.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
	Optional<Pedido> obterPedidoCompleto(Integer id);
	void atualizarStatus(Integer id, StatusPedido statusPedido);
	
	boolean test();
}
