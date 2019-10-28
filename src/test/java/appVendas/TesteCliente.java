package appVendas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import enumeration.TipoPessoa;
import model.Cliente;
import model.Endereco;

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

}
