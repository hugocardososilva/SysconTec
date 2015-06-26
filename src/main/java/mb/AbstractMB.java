package mb;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;



public class AbstractMB {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractMB() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
		
		FacesMessage messagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro:", message);  
        
        FacesContext.getCurrentInstance().addMessage(null, messagem);  
	}
	
	protected void displayInfoMessageToUser(String message) {
		
			FacesMessage messagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:", message);  
        
        FacesContext.getCurrentInstance().addMessage(null, messagem); 
	}
	
	protected void closeDialog(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}
	
	protected void keepDialogOpen(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}
	
	protected RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}

}
