package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.model.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {

	private Fabricante fabricanteCadastro;
	private List<Fabricante> listaFabricantes;
	private List<Fabricante> listaFabricantesFiltrados;
	private String acao;
	private Long codigo;

	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);
			fabricanteCadastro = new Fabricante();
			FacesUtil.adicionarMensagemInfo("Fabricante salvo com sucesso!");
		} catch (Exception e) {
			// e.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar incluir um fabricante: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.remover(fabricanteCadastro);
			setFabricanteCadastro(new Fabricante());
			FacesUtil.adicionarMensagemInfo("Fabricante removido com sucesso!");
		} catch (Exception e) {
			// e.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar remover o fabricante: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.editar(fabricanteCadastro);
			FacesUtil.adicionarMensagemInfo("Fabricante editado com sucesso!");
		} catch (Exception e) {
			// e.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar editar um fabricante: " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			setListaFabricantes(fabricanteDAO.listar());
		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar buscar um fabricante: " + e.getMessage());
		}
	}

	public void carregarCadastro() {

		try {
			if (codigo != null) {
				setFabricanteCadastro(new FabricanteDAO().buscarPorId(codigo));
			} else {
				setFabricanteCadastro(new Fabricante());
			}

		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar obter os dados do fabricante: " + e);
		}
	}

	public void novo() {
		fabricanteCadastro = new Fabricante();
	}

	public Fabricante getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public List<Fabricante> getListaFabricantesFiltrados() {
		return listaFabricantesFiltrados;
	}

	public void setListaFabricantesFiltrados(List<Fabricante> listaFabricantesFiltrados) {
		this.listaFabricantesFiltrados = listaFabricantesFiltrados;
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

}
