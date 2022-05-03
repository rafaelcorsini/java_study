package io.github.rafaelcorsini.study.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.rafaelcorsini.study.domain.entity.Cliente;
import io.github.rafaelcorsini.study.domain.entity.ItemPedido;
import io.github.rafaelcorsini.study.domain.entity.Pedido;
import io.github.rafaelcorsini.study.domain.entity.Produto;
import io.github.rafaelcorsini.study.domain.enums.StatusPedido;
import io.github.rafaelcorsini.study.domain.repository.ClienteRepository;
import io.github.rafaelcorsini.study.domain.repository.ItemPedidoRepository;
import io.github.rafaelcorsini.study.domain.repository.PedidoRepository;
import io.github.rafaelcorsini.study.domain.repository.ProdutoRepository;
import io.github.rafaelcorsini.study.exception.PedidoNaoEncontradoException;
import io.github.rafaelcorsini.study.exception.RegraNegocioException;
import io.github.rafaelcorsini.study.rest.dto.ItemPedidoDTO;
import io.github.rafaelcorsini.study.rest.dto.PedidoDTO;
import io.github.rafaelcorsini.study.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor	
public class PedidoServiceImpl implements PedidoService {
	
	private final ClienteRepository clienteRepository;
	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;
	private final ItemPedidoRepository itemPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clienteRepository
								.findById(idCliente)
								.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido!"));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());

		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(itensPedido);
		
		pedido.setItems(itensPedido);
		
		return pedido;
	}
	
	private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
		if (itens.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
		}
		
		return itens	
				.stream()
				.map( dto -> {
						Integer idProduto = dto.getProduto();
						Produto produto = produtoRepository
							.findById(idProduto)
							.orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));
						
						ItemPedido itemPedido = new ItemPedido();
						itemPedido.setQuantidade(dto.getQuantidade());
						itemPedido.setPedido(pedido);
						itemPedido.setProduto(produto);
						
						return itemPedido;
				}).collect(Collectors.toList());
	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidoRepository.findByIdFetchItens(id);
	}

	@Override
	@Transactional
	public void atualizarStatus(Integer id, StatusPedido statusPedido) {
		pedidoRepository.findById(id)
						.map(pedido -> {
							pedido.setStatus(statusPedido);
							return pedidoRepository.save(pedido);
						}).orElseThrow(() -> new PedidoNaoEncontradoException());
						
		
	}

	@Override
	public boolean test() {
		return true;
	}

	@Override
	public List<Pedido> getPedidoPorStatus(StatusPedido status) {
		return pedidoRepository.findPedidoByStatus(status);
	}
}
