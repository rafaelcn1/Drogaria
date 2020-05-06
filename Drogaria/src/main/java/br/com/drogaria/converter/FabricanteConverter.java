package br.com.drogaria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.model.Fabricante;

@FacesConverter("fabricanteConverter")
public class FabricanteConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			Fabricante fabricante = new FabricanteDAO().buscarPorId(codigo);
			return fabricante;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objeto) {
		try {
			Fabricante fabricante = (Fabricante) objeto;
			Long codigo = fabricante.getCodigo();
			return codigo.toString();
		} catch (Exception e) {
			return null;
		}
	}

}
