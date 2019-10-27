package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PesquisaPedidosBean {

	private List<Integer> pedidosFiltados;

	public PesquisaPedidosBean() {
		pedidosFiltados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			pedidosFiltados.add(i);
		}
	}

	public List<Integer> getPedidosFiltados() {
		return pedidosFiltados;
	}

	public void setPedidosFiltados(List<Integer> pedidosFiltados) {
		this.pedidosFiltados = pedidosFiltados;
	}

}
