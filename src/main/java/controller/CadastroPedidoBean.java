package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import enumeration.FormaPagamento;
import model.Cliente;
import model.EnderecoEntrega;
import model.Pedido;
import model.Usuario;
import repository.Clientes;
import repository.Usuarios;
import service.CadastroPedidoService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios; 

	@Inject
	private Clientes clientes;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;

	private Pedido pedido;
	private List<Usuario> vendedores;

	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public CadastroPedidoBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			this.vendedores = this.usuarios.vendedores();
			
			this.recalcularPedido(); // não sei se é necessário
		}
	}

	public void salvar() {
		this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
	}
	
	public void recalcularPedido() {
		if (this.pedido != null)  { // Não sei se é necessário
			this.pedido.recalcularValorTotal();
		}
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome) {
		return this.clientes.porNome(nome);
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
	public boolean isEditando() {
		return this.pedido.getId() != null;
	}
	
}
