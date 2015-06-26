package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


import model.TipoServico;

import dao.DAOTipoServico;


@FacesConverter(forClass= TipoServico.class)
public class TipoServicoConverter implements Converter {
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		DAOTipoServico dao= new DAOTipoServico();
		dao.open();
		dao.begin();
		TipoServico tipo= new TipoServico();
		try {
			Long id= Long.parseLong(value);
			tipo =dao.find(id);
			System.out.println("convertendo tipo   " + id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "Tipo inválido"));
		}

		return tipo;
	}


	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value== null){
			return null;
		}
		TipoServico tipo = (TipoServico) value;
		return String.valueOf(tipo.getId());
	}


}
