package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Produto;
import repository.Produtos;
import repository.filter.ProdutoFilter;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos; 

	private ProdutoFilter filtro;
	private List<Produto> produtosFiltados;
	
	private Produto produtosSelecionado;
	
	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
	
	public void excluir() {
		produtos.remover(produtosSelecionado);
		produtosFiltados.remove(produtosSelecionado);
		
		FacesUtil.addInfoMessage("Produto " + produtosSelecionado.getSku() 
			+ " exclído com sucesso");
	}
	
	public void pesquisar() {
		produtosFiltados = produtos.filtrados(filtro);
	}
	
	public List<Produto> getProdutosFiltados() {
		return produtosFiltados;
	}
	
	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public Produto getProdutosSelecionado() {
		return produtosSelecionado;
	}

	public void setProdutosSelecionado(Produto produtosSelecionado) {
		this.produtosSelecionado = produtosSelecionado;
	}
}
