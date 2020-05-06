package br.com.drogaria.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.model.Funcionario;

public class FuncionarioDAOTeste {
	
	@Test
	@Ignore
	public void salvar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario1 = new Funcionario();
		funcionario1.setNome("Funcionario C");
		funcionario1.setCpf("111.111.111-11");
		funcionario1.setFuncao("administrativo");
		funcionario1.setSenha("123456");
		
		
		Funcionario funcionario2 = new Funcionario();
		funcionario2.setNome("Funcionario D");
		funcionario2.setCpf("222.222.222-22");
		funcionario2.setFuncao("fiscal");
		funcionario2.setSenha("123456");
		
		dao.salvar(funcionario1);
		dao.salvar(funcionario2);
	}
	
	@Test
	@Ignore
	public void listar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> listar = dao.listar();
		
		for (Funcionario funcionario : listar) {
			System.out.println(funcionario.toString());
		}
	}
	
	@Test
	@Ignore
	public void buscaPorId() {
		Funcionario funcionario = null;
		FuncionarioDAO dao = new FuncionarioDAO();
		funcionario = dao.buscarPorId(6L);
		System.out.println(funcionario.toString());
	}
	
	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorId(6L);
		dao.remover(funcionario);
	}
	
	@Test
	public void editar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorId(38L);
		funcionario.setSenha("00000000");
		dao.editar(funcionario);
	}
	
	@Test
	@Ignore
	public void autenticar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.autenticar("051.961.314-77", "12345678");
		Assert.assertNotNull(funcionario);
		System.out.println("Funcionario: " + funcionario.toString());
	}
}
