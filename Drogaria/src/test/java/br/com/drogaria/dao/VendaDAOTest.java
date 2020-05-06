package br.com.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.model.Funcionario;
import br.com.drogaria.model.Venda;

public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Venda venda = new Venda();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorId(3L);

		venda.setFuncionario(funcionario);
		venda.setValorTotal(new BigDecimal(160.50));
		venda.setHorario(new Date());
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
	}

	@Test
	@Ignore
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();

		List<Venda> vendas = vendaDAO.listar();

		for (Venda venda : vendas) {
			System.out.println(venda.toString());
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		Venda venda = new VendaDAO().buscarPorId(13L);
		System.out.println(venda.toString());

	}

	@Test
	@Ignore
	public void excluir() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorId(14L);
		vendaDAO.excluir(venda);
	}

	@Test
	@Ignore
	public void editar() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorId(15L);
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(149.99));
		vendaDAO.editar(venda);
	}

	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void buscar() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		VendaFilter filtro = new VendaFilter();
		//filtro.setDataInicial(format.parse("05/05/2020"));
		//filtro.setDataFinal(format.parse("06/05/2020"));

		List<Venda> vendas = new VendaDAO().buscar(filtro);

		for (Venda venda : vendas) {
			System.out.println(venda.toString());
		}

	}

}
