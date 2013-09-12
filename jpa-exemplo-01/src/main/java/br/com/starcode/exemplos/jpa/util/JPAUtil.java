package br.com.starcode.exemplos.jpa.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Rotinas gerais de interface com o JPA
 */
public class JPAUtil {

	private static EntityManagerFactory factory;
	private static Map<Object, Object> connectionProperties = new HashMap<Object, Object>();
	
	/**
	 * Cria uma entity manager factory única e a retorna em todas as demais chamadas
	 */
	public static EntityManagerFactory getFactory() {
		
		if (factory == null || !factory.isOpen()) {
			
			factory = Persistence.createEntityManagerFactory("Exemplo", connectionProperties);
			
		}
		return factory;
		
	}
	
	/**
	 * Cria um entity manager
	 */
	public static EntityManager getEntityManager() {

		return getFactory().createEntityManager();
	
	}
	
}
