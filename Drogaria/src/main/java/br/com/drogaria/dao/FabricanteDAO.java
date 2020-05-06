package br.com.drogaria.dao;

import java.util.List;

import br.com.drogaria.model.Fabricante;
import br.com.drogaria.util.DAO;

public class FabricanteDAO {

	public void salvar(Fabricante fabricante) {
		DAO<Fabricante> dao = new DAO<Fabricante>(Fabricante.class);
		dao.salvar(fabricante);
	}

	public List<Fabricante> listar() {
		DAO<Fabricante> dao = new DAO<Fabricante>(Fabricante.class);
		return dao.listar();
	}

	public Fabricante buscarPorId(long id) {
		DAO<Fabricante> dao = new DAO<Fabricante>(Fabricante.class);
		return dao.buscarPorId(id);
	}
	
	public void remover(Fabricante fabricante) {
		DAO<Fabricante> dao = new DAO<Fabricante>(Fabricante.class);
		dao.remove(fabricante);
	}
	
	public void editar(Fabricante fabricante) {
		DAO<Fabricante> dao = new DAO<Fabricante>(Fabricante.class);
		dao.editar(fabricante);
	}
}
