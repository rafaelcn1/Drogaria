package br.com.drogaria.util;

import javax.persistence.EntityManager;

import org.junit.Test;

public class JPAUtilTeste {
	
	@Test
	public void jpaUtil() {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.close();
	}
}
