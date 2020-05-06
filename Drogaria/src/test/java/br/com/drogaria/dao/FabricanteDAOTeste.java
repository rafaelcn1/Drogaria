package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.model.Fabricante;

public class FabricanteDAOTeste {
	
	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante1 = new Fabricante();
		fabricante1.setDescricao("Fabricante C");
		
		Fabricante fabricante2 = new Fabricante();
		fabricante2.setDescricao("Fabricante D");
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(fabricante1);
		dao.salvar(fabricante2);
	}
	
	@Test
	@Ignore
	public void listar(){
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> listar = dao.listar();
		
		for (Fabricante fabricante : listar) {
			System.out.println(fabricante.toString());
		}
	}
	
	@Test
	@Ignore
	public void buscaPorId() {
		Fabricante fabricante = null;
		FabricanteDAO dao = new FabricanteDAO();
		fabricante = dao.buscarPorId(7L);
		System.out.println(fabricante.getDescricao());
	}
	
	@Test
	@Ignore
	public void excluir() {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscarPorId(4L);
		dao.remover(fabricante);
	}
	
	@Test
	@Ignore
	public void editar() {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscarPorId(8L);
		fabricante.setDescricao("Fabricante Editado");
		dao.editar(fabricante);
	}
}
