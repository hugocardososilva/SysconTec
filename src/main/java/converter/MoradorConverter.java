package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Morador;

import dao.DAO;
import dao.DAOMorador;

@FacesConverter(forClass= Morador.class)
public class MoradorConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
			DAOMorador daom= new DAOMorador();
			DAO dao = new DAO();
			dao.open();
			dao.begin();
			
			Morador m = new Morador();
		try {
			Long id= Long.parseLong(value);
			m =daom.find(id);
				
			
			return m;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "Tipo inválido"));
		}

		
	}


	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value!= null){
			
			Morador m = (Morador) value;
			
			return String.valueOf((Long)m.getId());
		}else{
		
		return null;
		}
	}
}
