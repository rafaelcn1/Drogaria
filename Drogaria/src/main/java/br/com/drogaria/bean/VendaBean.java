package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.model.Funcionario;
import br.com.drogaria.model.Item;
import br.com.drogaria.model.Produto;
import br.com.drogaria.model.Venda;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;

	private List<Item> listaItens;

	private Venda vendaCadastro;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private VendaFilter filtro;
	
	private List<Venda> listaVendasFiltradas;

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setHorario(new Date());
			vendaCadastro.setValorTotal(new BigDecimal(0.00));
			vendaCadastro.setQuantidade(0L);
			System.out.println(vendaCadastro.getQuantidade());
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public List<Item> getListaItens() {
		if (listaItens == null) {
			listaItens = new ArrayList<Item>();
		}
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public VendaFilter getFiltro() {
		
		if (filtro == null) {
			filtro = new VendaFilter();
		}
		return filtro;
	}

	public void setFiltro(VendaFilter filtro) {
		this.filtro = filtro;
	}
	
	

	public List<Venda> getListaVendasFiltradas() {
		return listaVendasFiltradas;
	}

	public void setListaVendasFiltradas(List<Venda> listaVendasFiltradas) {
		this.listaVendasFiltradas = listaVendasFiltradas;
	}

	public void carregarProdutos() {
		try {
			setListaProdutos(new ProdutoDAO().listar());
		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar buscar um fabricante: " + e.getMessage());
		}
	}

	public void adicionarProduto(Produto produto) {
		int indexEncontrado = -1;

		for (int i = 0; i < getListaItens().size() && indexEncontrado < 0; i++) {
			Item itemTemp = getListaItens().get(i);

			if (itemTemp.getProduto().equals(produto)) {
				indexEncontrado = i;
			}
		}

		Item item = new Item();
		item.setProduto(produto);

		if (indexEncontrado < 0) {
			item.setQuantidade(1L);
			item.setValor(produto.getPreco());
			getListaItens().add(item);
		} else {
			Item itemTemp = getListaItens().get(indexEncontrado);
			item.setQuantidade(itemTemp.getQuantidade() + 1L);
			item.setValor(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			getListaItens().set(indexEncontrado, item);
		}

		getVendaCadastro().setValorTotal(getVendaCadastro().getValorTotal().add(produto.getPreco()));
		getVendaCadastro().setQuantidade(getVendaCadastro().getQuantidade() + 1L);
		System.out.println(getVendaCadastro().getQuantidade());
	}

	public void removerItem(Item item) {
		int indexEncontrado = -1;

		for (int i = 0; i < getListaItens().size() && indexEncontrado < 0; i++) {
			Item itemTemp = getListaItens().get(i);

			if (itemTemp.getProduto().equals(item.getProduto())) {
				indexEncontrado = i;
			}
		}

		if (indexEncontrado > -1) {
			getListaItens().remove(indexEncontrado);
			getVendaCadastro().setValorTotal(getVendaCadastro().getValorTotal().subtract(item.getValor()));
			getVendaCadastro().setQuantidade(getVendaCadastro().getQuantidade() - item.getQuantidade());
		}
		System.out.println(getVendaCadastro().getQuantidade());

	}

	public void carregarDadosVenda() {
		getVendaCadastro().setHorario(new Date());
		System.out.println(getVendaCadastro().getHorario());

		Funcionario funcionario = new FuncionarioDAO()
				.buscarPorId(getAutenticacaoBean().getFuncionarioLogado().getCodigo());
		getVendaCadastro().setFuncionario(funcionario);
	}

	public void salvar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			Long codigoVenda = vendaDAO.salvar(getVendaCadastro());
			Venda vendaFK = vendaDAO.buscarPorId(codigoVenda);

			for (Item item : listaItens) {
				item.setVenda(vendaFK);
				new ItemDAO().salvar(item);
			}

			setVendaCadastro(new Venda());
			getVendaCadastro().setValorTotal(new BigDecimal(0));
			setListaItens(new ArrayList<Item>());

			FacesUtil.adicionarMensagemInfo("Venda salva com sucesso!");
		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar salvar a venda: " + e.getMessage());
		}
	}
	
	public void busca() {
		try {
			setListaVendasFiltradas(new VendaDAO().buscar(getFiltro()));
		} catch (Exception e) {
			// TODO: handle exception
			FacesUtil.adicionarMensagemErro("Erro ao tentar buscar uma venda!");
		}

	}

}
