package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.model.Fabricante;
import br.com.drogaria.model.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produtoCadastro;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private String acao;
	private Long codigo;
	
	private List<Fabricante> listaFabricante;

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);
			produtoCadastro = new Produto();
			FacesUtil.adicionarMensagemInfo("Fabricante salvo com sucesso!");
		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar incluir um fabricante: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoCadastro);
			setProdutoCadastro(new Produto());
			FacesUtil.adicionarMensagemInfo("Fabricante removido com sucesso!");
		} catch (Exception e) {
			// e.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar remover o fabricante: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);
			FacesUtil.adicionarMensagemInfo("Fabricante editado com sucesso!");
		} catch (Exception e) {
			// e.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar editar um fabricante: " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			setListaProdutos(produtoDAO.listar());
		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar buscar um fabricante: " + e.getMessage());
		}
	}

	public void carregarCadastro() {

		try {
			if (codigo != null) {
				setProdutoCadastro(new ProdutoDAO().buscarPorId(codigo));
			} else {
				setProdutoCadastro(new Produto());
			}
			
			setListaFabricante(new FabricanteDAO().listar());

		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar obter os dados do fabricante: " + e);
		}
	}

	public void novo() {
		produtoCadastro = new Produto();
	}

	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getlistaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setlistaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<Fabricante> getListaFabricante() {
		return listaFabricante;
	}

	public void setListaFabricante(List<Fabricante> listaFabricante) {
		this.listaFabricante = listaFabricante;
	}

}
