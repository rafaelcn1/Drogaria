package br.com.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAO<T> {
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		super();
		this.classe = classe;
	}

	public Class<T> getClasse() {
		return classe;
	}

	public void adicionar(T t) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		try {
			manager.persist(t);
			manager.getTransaction().commit();
		} finally {
			// TODO: handle finally clause
			manager.close();
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> listarTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("from ").append(getClasse().getSimpleName());
			System.out.println(sql.toString());
			Query query = manager.createQuery(sql.toString());
			System.out.println("Query: " + query);
			List resultList = query.getResultList();
			return resultList;

		} finally {
			// TODO: handle finally clause
		}

	}

	// http://www.universidadejava.com.br/materiais/jpa-exemplo-crud/
	public void remover(int id) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		try {
			T t = manager.find(getClasse(), id);
			manager.remove(t);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public T buscarPorId(int id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		try {
			T find = manager.find(getClasse(), id);
			return find;
		} finally {
			manager.close();
		}
	}

}
