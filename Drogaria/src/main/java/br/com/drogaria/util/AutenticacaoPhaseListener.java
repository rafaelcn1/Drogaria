package br.com.drogaria.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.drogaria.bean.AutenticacaoBean;
import br.com.drogaria.model.Funcionario;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {

	
	@Override
	public void afterPhase(PhaseEvent event) {
		// Depois das ações
		// Capturar o contexto da aplicação JSF
		FacesContext facesContext = event.getFacesContext();

		// Pegar a página corrente
		UIViewRoot uiViewRoot = facesContext.getViewRoot();

		// serve para navegar a página atual
		String paginaAtual = uiViewRoot.getViewId();

		boolean paginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");

		if (!paginaAutenticacao) {
			ExternalContext externalContext = facesContext.getExternalContext();

			// É um mapa onde tem todas as variaveis da sessão da aplicação
			Map<String, Object> mapa = externalContext.getSessionMap();

			// atraves do mapa, consegue pegar o nome do ManageBean, não é o nome da classe
			// e sim do objeto mesmo.
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) mapa.get("autenticacaoBean");

			// Agora podemos pegar o objeto do funcionario logado
			Funcionario funcionario = autenticacaoBean.getFuncionarioLogado();

			// tratativa para evitar acesso as paginas sem autenticação
			if (funcionario.getFuncao() == null) {
				FacesUtil.adicionarMensagemErro("Funcionário não autenticado!");

				// Mudando a página
				Application application = facesContext.getApplication();

				// Permitindo fazer navegação por páginas
				NavigationHandler navigationHandler = application.getNavigationHandler();

				// força a pagina que queremos
				navigationHandler.handleNavigation(facesContext, null, "autenticacao.xhtml?faces-redirect=true");

			}

		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// antes da ação "agir"

	}

	@Override
	public PhaseId getPhaseId() {
		// A fase que o Phaser Listener irá trabalhar
		// Restore_View, momento que reconstroi a arvore de componentes
		return PhaseId.RESTORE_VIEW;
	}

}
