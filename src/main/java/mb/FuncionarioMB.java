package mb;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;

import model.Funcionario;
import model.Morador;
import model.Telefone;
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
	private Telefone telefone;
	private List<Funcionario> funcionarios= new ArrayList<Funcionario>();
	
	public FuncionarioMB() {
		System.out.println("novo mb de funcionario " + this.toString());
		
	}
	@PostConstruct
	public void init(){
		this.novo= false;
		this.editar= false;
		this.funcionario= new Funcionario();
		this.telefone= new Telefone();
		
		
	}
	
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
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
	public void removerTelefone(){
		this.funcionario.removeTelefone(telefone);
		
	}
	public void salvarTelefone(){
		this.funcionario.addTelefone(this.telefone);
		this.telefone= new Telefone();
	}
	public void resetTelefone(){
		this.telefone= new Telefone();
	}
	
	@Override
	public String toString() {
		return "FuncionarioMB [funcionario=" + funcionario + ", novo=" + novo
				+ ", editar=" + editar + "]";
	}
	
	
	
}
