package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import enumeration.StatusPedido;
import model.Pedido;
import repository.Pedidos;
import repository.filter.PedidoFilter;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	private PedidoFilter filtro;
	private List<Pedido> pedidosFiltados;

	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
		pedidosFiltados = new ArrayList<>();
	}
	
	public void pesquisar() {
		pedidosFiltados = pedidos.filtrados(filtro);
	}

	public List<Pedido> getPedidosFiltados() {
		return pedidosFiltados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}
	
	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}

	public void setFiltro(PedidoFilter filtro) {
		this.filtro = filtro;
	}

}
