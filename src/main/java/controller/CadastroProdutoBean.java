package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import model.Categoria;
import model.Produto;
import repository.Categorias;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias; 
	
	private Produto produto;

	@NotNull
	private Categoria categoriaPai;
	
	private List<Categoria> categoriaRaizes;
	private List<Categoria> subcategorias;
	
	public CadastroProdutoBean() {
		produto = new Produto();
	}
	
	public void inicializar() {
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		//EntityManager manager = factory.createEntityManager();
		System.out.println("Inicializando...");

		if (FacesUtil.isNotPostBack()) {
			categoriaRaizes =  categorias.buscarCategoriaRaizes();
		}
		
		//manager.close();
	}
	
	public void carregarSubcategorias() {
		subcategorias = categorias.buscarSubCategoriasDe(categoriaPai);
	}

	public void salvar() {
		System.out.println("Categoria pai selecionada: " + categoriaPai.getDescricao());
		System.out.println("Subcategoria selecionada: " + produto.getCategoria().getDescricao());
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

	public Categorias getCategorias() {
		return categorias;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	
}
