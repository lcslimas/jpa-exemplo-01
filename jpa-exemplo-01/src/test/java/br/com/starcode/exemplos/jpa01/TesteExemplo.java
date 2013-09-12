/**
 * Autbank Projetos e Consultoria Ltda.
 * <br>
 * Criado em 11/09/2013 - 11:06:19
 * <br>
 * @version $Revision$ de $Date$<br>
 *           por $Author$<br>
 * @author luizricardo<br>
 */
package br.com.starcode.exemplos.jpa01;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.starcode.exemplos.jpa.util.JPAUtil;
import br.com.starcode.exemplos.jpa01.entity.Banco;
import br.com.starcode.exemplos.jpa01.entity.Cliente;

public class TesteExemplo {
	
	private EntityManager em;
	
	@BeforeClass
	public void iniciaEntityManager() {
		
		em = JPAUtil.getEntityManager();
		
	}
	
	@AfterClass
	public void finalizaEntityManager() {
		
		em.close();
		
	}
	
	@Test
	public void incluiBancos() {

		//inicia transação
		em.getTransaction().begin();
		
		//inclui dois bancos
		Banco banco1 = new Banco();
		banco1.setCodigo("001");
		banco1.setNome("Banco do Brasil");
		em.persist(banco1);
	
		Banco banco2 = new Banco();
		banco2.setCodigo("036");
		banco2.setNome("Bradesco");
		em.persist(banco2);

		//finaliza transação
		em.getTransaction().commit();
		
		//verifica se foram incluídos com sucesso
		Banco bancoIncluido1 = em.find(Banco.class, "001");
		Assert.assertEquals(bancoIncluido1.getCodigo(), banco1.getCodigo(), "Código do banco deveria ser igual");
		Assert.assertEquals(bancoIncluido1.getNome(), banco1.getNome(), "Nome do banco deveria ser igual");
		
		Banco bancoIncluido2 = em.find(Banco.class, "036");
		Assert.assertEquals(bancoIncluido2.getCodigo(), banco2.getCodigo(), "Código do banco deveria ser igual");
		Assert.assertEquals(bancoIncluido2.getNome(), banco2.getNome(), "Nome do banco deveria ser igual");
		
		//lista e verifica se existem apenas os dois
		List<Banco> listaBancos = em.createQuery("select b from Banco b", Banco.class).getResultList();
		
		Assert.assertEquals(2, listaBancos.size(), "Deveria haver dois bancos!");
		
	}
	
	@Test(dependsOnMethods = { "incluiBancos" })
	public void incluiClientes() {
		
		//inicia transação
		em.getTransaction().begin();
		
		//inclui dois clientes
		Cliente cliente1 = new Cliente();
		cliente1.setNome("João da Silva");
		Calendar dataNascimento1 = Calendar.getInstance();
		dataNascimento1.set(1983, 10, 30);
		cliente1.setNascimento(dataNascimento1.getTime());
		cliente1.setBanco(em.find(Banco.class, "001"));
		em.persist(cliente1);
		
		System.out.println("Cliente 1: " + cliente1.getId());
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Eduardo Ferreira");
		Calendar dataNascimento2 = Calendar.getInstance();
		dataNascimento1.set(1980, 1, 2);
		cliente2.setNascimento(dataNascimento2.getTime());
		cliente2.setBanco(em.find(Banco.class, "036"));
		em.persist(cliente2);
		
		System.out.println("Cliente 2: " + cliente2.getId());
		
		//finaliza transação
		em.getTransaction().commit();
		
		//verifica se foram incluídos com sucesso
		Cliente clienteIncluido1 = em.find(Cliente.class, 1l);
		Assert.assertEquals(clienteIncluido1.getId(), cliente1.getId(), "ID do cliente deveria ser igual");
		Assert.assertEquals(clienteIncluido1.getNome(), cliente1.getNome(), "Nome do cliente deveria ser igual");
		Assert.assertEquals(clienteIncluido1.getNascimento(), cliente1.getNascimento(), "Nascimento do cliente deveria ser igual");
		Assert.assertEquals(clienteIncluido1.getBanco(), cliente1.getBanco(), "Banco do cliente deveria ser igual");
		
		Banco bancoIncluido1 = clienteIncluido1.getBanco();
		Assert.assertEquals(bancoIncluido1.getCodigo(), "001");
		Assert.assertEquals(bancoIncluido1.getNome(), "Banco do Brasil");
		
		Cliente clienteIncluido2 = em.find(Cliente.class, 2l);
		Assert.assertEquals(clienteIncluido2.getId(), cliente2.getId(), "ID do cliente deveria ser igual");
		Assert.assertEquals(clienteIncluido2.getNome(), cliente2.getNome(), "Nome do cliente deveria ser igual");
		Assert.assertEquals(clienteIncluido2.getNascimento(), cliente2.getNascimento(), "Nascimento do cliente deveria ser igual");
		Assert.assertEquals(clienteIncluido2.getBanco(), cliente2.getBanco(), "Banco do cliente deveria ser igual");
		
		//lista e verifica se existem apenas os dois
		List<Cliente> listaClientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
		
		Assert.assertEquals(2, listaClientes.size(), "Deveria haver dois clientes!");
		
	}

}
