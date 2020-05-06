package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.model.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String autenticar() {
		try {
			setFuncionarioLogado(new FuncionarioDAO().autenticar(getFuncionarioLogado().getCpf(),
					(getFuncionarioLogado().getSenha())));
			
			if (getFuncionarioLogado() == null) {
				FacesUtil.adicionarMensagemErro("CPF e/ou senha inválidos!");
				return null;
			} else {
				FacesUtil.adicionarMensagemInfo("Bem Vindo " + getFuncionarioLogado().getNome());
				return "principal.xhtm?faces-redirect=true";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			FacesUtil.adicionarMensagemErro("Erro ao tentar autenticar no sistema:" + e);
			return null;
		}
		
		
	}
	
	public String sair() {
		setFuncionarioLogado(null);
		return "autenticacao.html?faces-redirect=true";
	}

}
