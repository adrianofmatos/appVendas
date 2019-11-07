package service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import enumeration.StatusPedido;
import model.Pedido;
import repository.Pedidos;
import util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}

}
