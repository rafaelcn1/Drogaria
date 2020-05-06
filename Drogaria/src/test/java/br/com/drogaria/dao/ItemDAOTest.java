package br.com.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.model.Item;
import br.com.drogaria.model.Produto;
import br.com.drogaria.model.Venda;

public class ItemDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Produto produto = new ProdutoDAO().buscarPorId(12L);
		
		Venda venda = new VendaDAO().buscarPorId(15L);
		
		Item item = new Item();
		item.setProduto(produto);
		item.setVenda(venda);
		item.setQuantidade(10L);
		item.setValor(new BigDecimal(12.50D));
		
		new ItemDAO().salvar(item);
		
		
		
	}
	
	@Test
	@Ignore
	public void listar() {
		ItemDAO itemDAO = new ItemDAO();
		
		List<Item> itens = itemDAO.listar();
		
		for (Item item : itens) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ItemDAO dao = new ItemDAO();
		Item item = dao.buscarPorId(17L);
		
		System.out.println(item.toString());
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		ItemDAO dao = new ItemDAO();
		Item item = dao.buscarPorId(18L);
		dao.excluir(item);
		
	}
	
	@Test
	@Ignore
	public void editar() {
		ItemDAO dao = new ItemDAO();
		Item item = dao.buscarPorId(18L);
		System.out.println(item.toString());
		
		item.setQuantidade(11L);
		dao.editar(item);
		
		
	}
}
