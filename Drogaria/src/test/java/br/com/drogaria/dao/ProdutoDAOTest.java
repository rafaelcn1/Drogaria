package br.com.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.model.Fabricante;
import br.com.drogaria.model.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		
		Fabricante fabricante = fabricanteDAO.buscarPorId(9L);
		
		Produto produto = new Produto();
		produto.setDescricao("Produto Y");
		produto.setPreco(new BigDecimal(17.65D));
		produto.setQuantidade(3L);
		produto.setFabricante(fabricante);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		List<Produto> produtos = produtoDAO.listar();
		
		for (Produto produto : produtos) {
			System.out.println(produto.toString());
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscarPorId(11L);
		
		System.out.println(produto.toString());
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscarPorId(11L);
		dao.excluir(produto);
		
	}
	
	@Test
	public void editar() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscarPorId(12L);
		System.out.println(produto.toString());
		
		Fabricante fabricante = new FabricanteDAO().buscarPorId(8L);
		
		produto.setDescricao("Produto Y Editado");
		produto.setFabricante(fabricante);
		dao.editar(produto);
		
		
	}
}
