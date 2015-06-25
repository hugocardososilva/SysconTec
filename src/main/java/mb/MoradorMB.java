package mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Morador;
import model.Telefone;

@ManagedBean
@ViewScoped
public class MoradorMB extends AbstractMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6252223090720522948L;
	private boolean novo;
	private boolean editar;
	private Telefone telefone;
	private Morador morador;
	private List<Morador> moradores= new ArrayList<Morador>();
	
	public MoradorMB() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init(){
		this.novo= false;
		this.editar= false;
		this.morador= new Morador();
		this.telefone= new Telefone();
		
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
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public Morador getMorador() {
		return morador;
	}
	public void setMorador(Morador morador) {
		this.morador = morador;
	}
	public List<Morador> getMoradores() {
		return moradores;
	}
	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}
	
	public void salvarTelefone(){
		this.morador.addTelefone(this.telefone);
		this.telefone= new Telefone();
	}
	public void resetTelefone(){
		this.telefone= new Telefone();
	}
	public void novoMorador(){
		this.novo= true;
	}
	
}
