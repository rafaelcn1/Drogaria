package br.com.drogaria.dao;

import java.util.List;

import br.com.drogaria.model.Produto;
import br.com.drogaria.util.DAO;

public class ProdutoDAO {
	public void salvar(Produto produto) {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.salvar(produto);
	}
	
	public List<Produto> listar(){
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		return dao.listar();
	}
	
	public Produto buscarPorId(Long codigo) {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		return dao.buscarPorId(codigo);
	}
	
	public void excluir(Produto produto) {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.remove(produto);
	}
	
	public void editar(Produto produto) {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.editar(produto);
	}
}
