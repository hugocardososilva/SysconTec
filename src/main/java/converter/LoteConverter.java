package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Lote;
import dao.DAOLote;
@FacesConverter(forClass= Lote.class)
public class LoteConverter implements Converter {


	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		DAOLote dao = new DAOLote();
		dao.open();
		dao.begin();
		Lote lote= new Lote();
		try {
			Long id= Long.parseLong(value);
			lote =dao.find(id);
			System.out.println("convertendo lote   " + lote.toString());
			return lote;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "Tipo de lote inválido"));
		}

		
	}


	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value!= null){
			Lote lote = (Lote) value;
			System.out.println(lote.toString());
			return String.valueOf((Long)lote.getId());
		}else{
			return null;
		
		}
	}

}
