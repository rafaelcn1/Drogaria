package br.com.drogaria.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class DAO<T> {
	private Class<T> classe;

	public DAO(Class<T> classe) {
		super();
		this.classe = classe;
	}

	public Class<T> getClasse() {
		return classe;
	}

	public void salvar(T t) {
		EntityManager manager = new JPAUtil().getEntityManager();

		EntityTransaction transaction = null;
		if (transaction == null) {
			transaction = manager.getTransaction();
		}
		try {
			transaction.begin();

			manager.persist(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		} finally {
			manager.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> listar() {
		List<T> todos = null;
		EntityManager manager = new JPAUtil().getEntityManager();
		Query consulta = null;

		// String namedQuery = getClasse().getSimpleName() + ".listar";
		// System.out.println(namedQuery);
		String sql = "select " + getClasse().getSimpleName().toLowerCase() + " From " + getClasse().getSimpleName()
				+ " " + getClasse().getSimpleName().toLowerCase();
		try {
			manager.getTransaction().begin();
			// consulta = manager.createNamedQuery(namedQuery);
			consulta = manager.createQuery(sql);
			todos = consulta.getResultList();
		} catch (Exception e) {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		} finally {
			manager.close();
		}

		return todos;

	}

	public T buscarPorId(Long id) {

		EntityManager manager = new JPAUtil().getEntityManager();

		EntityTransaction transaction = manager.getTransaction();
		T find = null;
		try {
			transaction.begin();
			find = manager.find(getClasse(), id);
		} catch (Exception e) {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		} finally {
			manager.close();
		}

		return find;
	}

	public void remove(T t) {
		EntityManager manager = new JPAUtil().getEntityManager();

		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.remove(manager.merge(t));
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		} finally {
			manager.close();
		}
	}

	public void editar(T t) {

		EntityManager manager = new JPAUtil().getEntityManager();

		EntityTransaction transaction = manager.getTransaction();
		if (!transaction.isActive()) {
			transaction.begin();
		}
		try {
			manager.merge(t);
			transaction.commit();
		} catch (Exception e) {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		} finally {
			manager.close();
		}

	}

}
