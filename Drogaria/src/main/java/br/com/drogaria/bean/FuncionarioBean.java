package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.model.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	
	private Funcionario funcionarioCadastro;
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;
	private String acao;
	private Long codigo;
	
	
	public Funcionario getFuncionarioCadastro() {
		if (funcionarioCadastro == null) {
			funcionarioCadastro = new Funcionario();
		}
		return funcionarioCadastro;
	}


	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}


	public void salvar() {
		try {
			FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();
			FuncionarioDAO.salvar(funcionarioCadastro);
			funcionarioCadastro = new Funcionario();
			FacesUtil.adicionarMensagemInfo("Funcionario salvo com sucesso!");
		} catch (Exception e) {
			// e.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar incluir um Funcionario: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();
			FuncionarioDAO.remover(funcionarioCadastro);
			setFuncionarioCadastro(new Funcionario());
			FacesUtil.adicionarMensagemInfo("Funcionario removido com sucesso!");
		} catch (Exception e) {
			// e.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar remover o Funcionario: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();
			FuncionarioDAO.editar(getFuncionarioCadastro());
			FacesUtil.adicionarMensagemInfo("Funcionario editado com sucesso!");
		} catch (Exception e) {
			// e.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar editar um Funcionario: " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();
			setListaFuncionarios(FuncionarioDAO.listar());
		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar buscar um Funcionario: " + e.getMessage());
		}
	}

	public void carregarCadastro() {

		try {
			if (codigo != null) {
				setFuncionarioCadastro(new FuncionarioDAO().buscarPorId(codigo));
			} else {
				setFuncionarioCadastro(new Funcionario());
			}

		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar obter os dados do Funcionario: " + e);
		}
	}

	public void novo() {
		funcionarioCadastro = new Funcionario();
	}


	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
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
