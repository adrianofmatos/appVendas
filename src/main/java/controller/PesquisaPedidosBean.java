package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

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
	private LazyDataModel<Pedido> model;

	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();

		model = new LazyDataModel<Pedido>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Pedido> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setPropriedadeOrdenacao(sortField);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				
				
				setRowCount(pedidos.quantidadeFiltrados(filtro));
				
				return pedidos.filtrados(filtro);
			}
		};
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

	public LazyDataModel<Pedido> getModel() {
		return model;
	}
}
