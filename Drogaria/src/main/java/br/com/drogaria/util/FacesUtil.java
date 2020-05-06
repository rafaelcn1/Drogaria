package br.com.drogaria.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesUtil {
	public static void adicionarMensagemInfo(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);

		// Capturar o contexto do JSF
		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Capturar informações do navegador
		ExternalContext externalContext = facesContext.getExternalContext();

		// Capturar o Flash, a memoria utilizando durante as requisições, temporaria
		// para pegar dados durante as requisições
		Flash flash = externalContext.getFlash();

		// Deixar a mensagem até outra requisição
		flash.setKeepMessages(true);

		facesContext.addMessage(null, message);
	}

	public static void adicionarMensagemErro(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);

		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Capturar informações do navegador
		ExternalContext externalContext = facesContext.getExternalContext();

		// Capturar o Flash, a memoria utilizando durante as requisições, temporaria
		// para pegar dados durante as requisições
		Flash flash = externalContext.getFlash();

		// Deixar a mensagem até outra requisição
		flash.setKeepMessages(true);

		facesContext.addMessage(null, message);

	}

	// Metodo para pegar os dados da url
	public static String getPara(String nome) {
		// Capturar o contexto da aplicação como um todo
		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Capturar o contexto do navegador
		ExternalContext externalContext = facesContext.getExternalContext();

		// Pegar o mapa do parametro, podendo ser todos os parametros da url,
		Map<String, String> parametros = externalContext.getRequestParameterMap();

		// pegando apenas o parametros que queremos
		String valor = parametros.get(nome);

		return valor;

	}
}
