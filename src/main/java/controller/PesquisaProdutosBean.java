package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PesquisaProdutosBean {

	private List<Integer> produtosFiltados;

	public PesquisaProdutosBean() {
		produtosFiltados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			produtosFiltados.add(i);
		}
	}

	public List<Integer> getProdutosFiltados() {
		return produtosFiltados;
	}

	public void setProdutosFiltados(List<Integer> produtosFiltados) {
		this.produtosFiltados = produtosFiltados;
	}
	
}
