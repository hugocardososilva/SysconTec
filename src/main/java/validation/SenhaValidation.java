package validation;

import java.io.Serializable;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;





import model.Prestador;

import org.primefaces.component.messages.Messages;

import dao.DAOPrestadorPessoa;


@FacesValidator("senhaValidator")
public class SenhaValidation implements Validator, Serializable {
	private DAOPrestadorPessoa dao= new DAOPrestadorPessoa();
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		boolean isValid= false;
		String coluna= (String) component.getAttributes().get("colSenha");
			try {
				dao.open();
				dao.begin();
				Prestador p= dao.findBySenha((String) value);
				System.out.println(p.toString()	);
				
				
			} catch (Exception e) {
				System.out.println(e.toString());
				isValid= true;
			}finally{
				dao.close();
			}
			
		if(!isValid){
			FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha:", "Já existe uma senha igual,"
					+ "por favor, escolha outra.");
//			context.addMessage(component.getClientId(context), msg);
			throw new ValidatorException(msg);
		}
	
		
	}
	

	

}
