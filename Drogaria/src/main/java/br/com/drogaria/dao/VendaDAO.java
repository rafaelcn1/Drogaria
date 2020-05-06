package br.com.drogaria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.model.Venda;
import br.com.drogaria.util.DAO;
import br.com.drogaria.util.JPAUtil;

public class VendaDAO {
	public Long salvar(Venda venda) {
		DAO<Venda> dao = new DAO<Venda>(Venda.class);
		venda.getCodigo();
		dao.salvar(venda);

		return venda.getCodigo();
	}

	public List<Venda> listar() {
		DAO<Venda> dao = new DAO<Venda>(Venda.class);
		return dao.listar();
	}

	public Venda buscarPorId(Long codigo) {
		DAO<Venda> dao = new DAO<Venda>(Venda.class);
		return dao.buscarPorId(codigo);
	}

	public void excluir(Venda venda) {
		DAO<Venda> dao = new DAO<Venda>(Venda.class);
		dao.remove(venda);
	}

	public void editar(Venda venda) {
		DAO<Venda> dao = new DAO<Venda>(Venda.class);
		dao.editar(venda);
	}

	@SuppressWarnings("unchecked")
	public List<Venda> buscar(VendaFilter filtro) {
		List<Venda> vendas = null;

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT venda FROM Venda venda ");

		if (filtro.getDataFinal() != null && filtro.getDataFinal() != null) {
			sql.append("WHERE venda.horario BETWEEN :dataInicial AND :dataFinal ");
		}

		sql.append("ORDER BY venda.horario");

		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			Query query = manager.createQuery(sql.toString());
			
			if (filtro.getDataFinal() != null && filtro.getDataFinal() != null) {
				query.setParameter("dataInicial", filtro.getDataInicial());
				query.setParameter("dataFinal", filtro.getDataFinal());
			}
			vendas = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		} finally {
			manager.close();
		}

		return vendas;
	}
}
