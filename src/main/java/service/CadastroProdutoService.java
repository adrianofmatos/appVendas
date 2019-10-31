package service;

import java.io.Serializable;

import javax.inject.Inject;

import model.Produto;
import repository.Produtos;
import util.jpa.Transactional;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	
	@Transactional
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtos.buscarPorSku(produto.getSku());
		
		if (produtoExistente != null ) {
			throw new NegocioException("Já existe um produto com o SKU informado!");
		}
		
		return produtos.guardar(produto);
	}

}
