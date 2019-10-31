package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Categoria;
import model.Produto;
import repository.Categorias;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias; 
	
	private Produto produto;
	
	private List<Categoria> categoriaRaizes;
	
	public CadastroProdutoBean() {
		produto = new Produto();
	}
	
	public void inicializar() {
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		//EntityManager manager = factory.createEntityManager();
		
		categoriaRaizes =  categorias.buscarCategoriaRaizes();
		
		//manager.close();
	}

	public void salvar() {
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}
	
}
