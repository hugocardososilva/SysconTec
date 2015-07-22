package mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAOCondominio;
import model.Condominio;
import model.Telefone;

@ManagedBean
@ViewScoped
public class CondominioMB extends AbstractMB implements Serializable{
	private DAOCondominio dao= new DAOCondominio();
	private Condominio condominio;
	private boolean novo;
	private boolean editar;
	private Telefone telefone;
	
	
	@PostConstruct
	public void init(){
		System.out.println("iniciando condominio");
		getCondominioBanco();
		this.telefone = new Telefone();
		editar= false;
		novo= false;
		
	}
	
	public String editarCondominio(){
		System.out.println(condominio.toString());
		dao.open();
		dao.begin();
		dao.merge(condominio);
		dao.commit();
		displayInfoMessageToUser("Condomínio editado com sucesso!");
		return "condominio-config";
	}
	
	public void habilitarEdicao(){
		this.editar= true;
		this.novo= false;
	}
	
	public void resetTelefone(){
		this.telefone= new Telefone();
	}
	
	public Condominio getCondominio() {
		return condominio;
	}

	public Condominio getCondominioBanco() {
		dao.open();
		dao.begin();
		condominio= dao.find(1L);
		dao.close();
		return condominio;
	}
	public void salvarTelefone(){
		dao.open();
		dao.begin();
		System.out.println("novo telefone");
		condominio.addTelefone(telefone);
		dao.merge(condominio);
		dao.commit();
		resetTelefone();
	}
	public void removerTelefone(){
		
		System.out.println("removendo telefone");
		condominio.removeTelefone(telefone);
		
		resetTelefone();
	}
	
	

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
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
	

}
