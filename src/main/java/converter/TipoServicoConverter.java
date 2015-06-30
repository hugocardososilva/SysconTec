package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.TipoServico;
import dao.DAO;
import dao.DAOTipoServico;


@FacesConverter(forClass= TipoServico.class)
public class TipoServicoConverter implements Converter {
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
			DAOTipoServico daot= new DAOTipoServico();
			DAO dao = new DAO();
			dao.open();
			dao.begin();
			
			TipoServico tipo= new TipoServico();
		try {
			Long id= Long.parseLong(value);
			tipo =daot.find(id);
				
			System.out.println("convertendo tipo   " + id);
			return tipo;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "Tipo inválido"));
		}

		
	}


	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value!= null){
			TipoServico tipo = (TipoServico) value;
			System.out.println(tipo.toString());
			return String.valueOf((Long)tipo.getId());
		}else{
		
		return null;
		}
	}


}
