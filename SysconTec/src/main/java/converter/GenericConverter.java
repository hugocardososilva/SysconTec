package converter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import dao.DAOObject;
import model.Lote;

@FacesConverter("generic")
public class GenericConverter implements Converter ,Serializable {


	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		 if(value != null && value.trim().length() > 0) {
			try {
				System.out.println("getAsobject" +this.getAttributesFrom(component).get(value).toString());
				return this.getAttributesFrom(component).get(value);
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro de convers�o", "numero invalido"));
			}
			 
			 
			 
		 }else{
			 return null;
		 }
		
	}


	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		  if (value != null
	                && !"".equals(value)) {
			 
			  GenericInterface gi= (GenericInterface) value;
			  System.out.println(value.toString());
			  this.addAttribute(component, gi);
			  Long i = gi.getId();
			  System.out.println("ID  " +i.toString());
			  if(i!= 0){
				  return String.valueOf(i);
			  }
		  }
	       return null;
	    
		
	}
	protected void addAttribute(UIComponent component, GenericInterface o) {
        String key = String.valueOf(o.getId()); // codigo da empresa como chave neste caso
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

	

}
