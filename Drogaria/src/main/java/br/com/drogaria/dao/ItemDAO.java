package br.com.drogaria.dao;

import java.util.List;

import br.com.drogaria.model.Item;
import br.com.drogaria.util.DAO;

public class ItemDAO {
	public void  salvar(Item item) {
		DAO<Item> dao = new DAO<Item>(Item.class);
		dao.salvar(item);
	}
	
	public List<Item> listar(){
		DAO<Item> dao = new DAO<Item>(Item.class);
		return dao.listar();
	}
	
	public Item buscarPorId(Long codigo) {
		DAO<Item> dao = new DAO<Item>(Item.class);
		return dao.buscarPorId(codigo);
	}
	
	public void excluir(Item item) {
		DAO<Item> dao = new DAO<Item>(Item.class);
		dao.remove(item);
	}
	
	public void editar(Item item) {
		DAO<Item> dao = new DAO<Item>(Item.class);
		dao.editar(item);
	}
}
