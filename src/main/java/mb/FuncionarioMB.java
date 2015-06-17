package mb;



import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;

import model.Morador;
import model.Usuario;

@ManagedBean
public class FuncionarioMB extends AbstractMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9171919960838291100L;
	private Usuario user;
	
	public FuncionarioMB() {
		user= new Morador();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	
	
}
