package validation;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import model.Usuario;
import dao.DAOUser;

@FacesValidator("loginUserValidator")
public class LoginUsuarioValidation implements Validator, Serializable{
	private DAOUser dao= new DAOUser();

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		boolean isValid= false;
		String coluna= (String) component.getAttributes().get("colLogin");
		Long idEdit= (Long) component.getAttributes().get("idLogin");
		
		try {
			dao.open();
			dao.begin();
			Usuario u = dao.getByLogin((String) value);
			System.out.println("Validando Login");
			if(u.getId()== idEdit){
				isValid=true;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			isValid= true;
		}finally{
			dao.close();
		}
		if(!isValid){
			FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login:", "Já existe esse login,"
					+ "por favor, escolha outro.");
//			context.addMessage(component.getClientId(context), msg);
			throw new ValidatorException(msg);
			
		}
		
	}
	

}
