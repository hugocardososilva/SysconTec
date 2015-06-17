package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Lote;
import dao.DAOLote;
@FacesConverter(forClass= model.Lote.class)
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
			System.out.println("convertendo lote   " + id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "Tipo de lote inv�lido"));
		}

		return lote;
	}


	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value== null){
			return "";
		}
		Lote lote = (Lote) value;
		return String.valueOf(lote.getId());
	}

}
