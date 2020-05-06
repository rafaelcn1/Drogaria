package br.com.drogaria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.drogaria.model.Funcionario;
import br.com.drogaria.util.DAO;
import br.com.drogaria.util.JPAUtil;

public class FuncionarioDAO {

	public void salvar(Funcionario funcionario) {
		DAO<Funcionario> dao = new DAO<Funcionario>(Funcionario.class);
		dao.salvar(funcionario);
	}

	public List<Funcionario> listar() {
		DAO<Funcionario> dao = new DAO<Funcionario>(Funcionario.class);
		return dao.listar();
	}

	public Funcionario buscarPorId(long id) {
		DAO<Funcionario> dao = new DAO<Funcionario>(Funcionario.class);
		return dao.buscarPorId(id);
	}
	
	public void remover(Funcionario funcionario) {
		DAO<Funcionario> dao = new DAO<Funcionario>(Funcionario.class);
		dao.remove(funcionario);
	}
	
	public void editar(Funcionario funcionario) {
		DAO<Funcionario> dao = new DAO<Funcionario>(Funcionario.class);
		dao.editar(funcionario);
	}
	
	public Funcionario autenticar(String cpf, String senha) {
		Funcionario funcionario = null;
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin();
			String sql = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.cpf = :cpf AND funcionario.senha = :senha";
			funcionario = (Funcionario) manager.createQuery(sql).setParameter("cpf", cpf).setParameter("senha", senha).getSingleResult();
			
		} catch (Exception e) {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		} finally {
			manager.close();
		}
		
		return funcionario;
	}
}
