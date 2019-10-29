package appVendas;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import enumeration.FormaPagamento;
import enumeration.StatusPedido;
import enumeration.TipoPessoa;
import model.Categoria;
import model.Cliente;
import model.Endereco;
import model.EnderecoEntrega;
import model.Grupo;
import model.ItemPedido;
import model.Pedido;
import model.Produto;
import model.Usuario;

public class TesteCliente {

	@Test
	public void test() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Cliente cliente = new Cliente();
		cliente.setNome("Adriano Falcão2");
		cliente.setEmail("adrianofalcao@gmail.com");
		cliente.setDocumentoReceitaFederal("123.456.789-12");
		cliente.setTipo(TipoPessoa.FISICA);

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua das Abobóras");
		endereco.setNumero("111");
		endereco.setCidade("Camaçari");
		endereco.setUf("BA");
		endereco.setCep("34800-00");
		endereco.setCliente(cliente);

		cliente.getEnderecos().add(endereco);

		manager.persist(cliente);

		trx.commit();
	}

	@Test
	public void testUsuarioGrupo() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Usuario usuario = new Usuario();
		usuario.setNome("Maria");
		usuario.setEmail("tatiana@abadia.com");
		usuario.setSenha("123");

		Grupo grupo = new Grupo();
		grupo.setNome("Vendedores");
		grupo.setDescricao("Vendedores da empresa");

		usuario.getGrupos().add(grupo);

		manager.persist(usuario);

		trx.commit();
	}

	@Test
	public void testProdutoCategoria() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		// instanciamos a categoria pai (Bebidas)
		Categoria categoriaPai = new Categoria();
		categoriaPai.setDescricao("Medicamento");

		// instanciamos a categoria filha (Refrigerantes)
		Categoria categoriaFilha = new Categoria();
		categoriaFilha.setDescricao("AS");
		categoriaFilha.setCategoriaPai(categoriaPai);

		// adicionamos a categoria Refrigerantes como filha de Bebidas
		categoriaPai.getSubcategorias().add(categoriaFilha);

		// ao persistir a categoria pai (Refrigerantes), a filha (Bebidas)
		// deve ser persistida também
		manager.persist(categoriaPai);

		// instanciamos e persistimos um produto
		Produto produto = new Produto();
		produto.setCategoria(categoriaFilha);
		produto.setNome("Guaraná 1L");
		produto.setQuantidadeEstoque(10);
		produto.setSku("GUA00122");
		produto.setValorUnitario(new BigDecimal(2.21));

		manager.persist(produto);

		trx.commit();
	}

	@Test
	public void testPedido() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Cliente cliente = manager.find(Cliente.class, 1L);
		Produto produto = manager.find(Produto.class, 1L);
		Usuario vendedor = manager.find(Usuario.class, 1L);

		EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
		enderecoEntrega.setLogradouro("Rua dos Mercados");
		enderecoEntrega.setNumero("1000");
		enderecoEntrega.setCidade("Uberlândia");
		enderecoEntrega.setUf("MG");
		enderecoEntrega.setCep("38400-123");

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(new Date());
		pedido.setDataEntrega(new Date());
		pedido.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
		pedido.setObservacao("Aberto das 08 às 18h");
		pedido.setStatus(StatusPedido.ORCAMENTO);
		pedido.setValorDesconto(BigDecimal.ZERO);
		pedido.setValorFrete(BigDecimal.ZERO);
		pedido.setValorTotal(new BigDecimal(23.2));
		pedido.setVendedor(vendedor);
		pedido.setEnderecoEntrega(enderecoEntrega);

		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setQuantidade(10);
		item.setValorUnitario(new BigDecimal(2.32));
		item.setPedido(pedido);

		pedido.getItens().add(item);

		manager.persist(pedido);

		trx.commit();
	}
}
