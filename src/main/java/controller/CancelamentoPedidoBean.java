package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import model.Pedido;
import service.CancelamentoPedidoService;
import util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CancelamentoPedidoService cancelamentoPedidoService;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void cancelarPedido() {
		this.pedido = this.cancelamentoPedidoService.cancelar(this.pedido);
		this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
		
		FacesUtil.addInfoMessage("Pedido cancelado com sucesso!");
	}

}
