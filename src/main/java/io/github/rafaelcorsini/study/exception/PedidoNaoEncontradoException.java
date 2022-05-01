package io.github.rafaelcorsini.study.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
}
