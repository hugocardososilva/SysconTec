package mb;



import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;

import model.Funcionario;
import model.Morador;
import model.Usuario;

@ManagedBean
@ViewScoped
public class FuncionarioMB extends AbstractMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9171919960838291100L;
	private Funcionario funcionario;
	private boolean novo;
	private boolean editar;
	
	public FuncionarioMB() {
		System.out.println("novo mb de funcionario " + this.toString());
		this.novo= false;
		this.editar= false;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public void novoFuncionario(){
		this.novo= true;
	}

	@Override
	public String toString() {
		return "FuncionarioMB [funcionario=" + funcionario + ", novo=" + novo
				+ ", editar=" + editar + "]";
	}
	
	
	
}
