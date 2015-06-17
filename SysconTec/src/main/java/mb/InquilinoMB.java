package mb;



import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;

import model.Morador;
import model.Usuario;

@ManagedBean
@ViewScoped
public class InquilinoMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario user;
	private String filename;
	
	
	
	
	public InquilinoMB() {
		user = new Morador();
	}


	public Usuario getUser() {
		return user;
	}


	public String getFilename() {
		return filename;
	}
	

	public void setFilename(String filename) {
		this.filename = filename;
	}


	public void setUser(Usuario user) {
		this.user = user;
	}

	public String novoInquilino(){
		return "/protected/funcionario/novo-inquilino.xhtml";
	}
	public void captura(CaptureEvent cEvent){
		ImageMB imb= new ImageMB();
		System.out.println("capturando imagem");
		ServletContext ctx= (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String filename = imb.oncapture(ctx, cEvent);
		System.out.println(filename);
		this.setFilename(filename);
		
		
	}

	

}
