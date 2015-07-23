package mb;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;

import dao.DAO;
import dao.DAOFuncionario;
import model.Funcionario;
import model.Grupo;
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
	private Grupo grupos;
	private boolean ver;
	private DAOFuncionario dao= new DAOFuncionario();
	public FuncionarioMB() {
		System.out.println("novo mb de funcionario " + this.toString());
		
	}
	@PostConstruct
	public void init(){
		this.novo= false;
		this.editar= false;
		this.funcionario= new Funcionario();
		this.telefone= new Telefone();
		this.ver= false;
		
	}
	
	
	public void novoFuncionario(){
		resetFuncionario();
		this.novo= true;
		this.editar= false;
	}
	public void salvar(){
		getNow();
		funcionario.setDataCadastro(new GregorianCalendar().getTime());
		dao.open();
		dao.begin();
		dao.persist(funcionario);
		dao.commit();
		displayInfoMessageToUser("Funcionário adicionado com sucesso!");
		this.editar= false;
		this.novo= false;
		limparFuncionario();
		
	}
	public String editar(){
		dao.open();
		dao.begin();
		dao.merge(funcionario);
		dao.commit();
		displayInfoMessageToUser("Funcionário editado com sucesso!");
		return "gerenciar-funcionarios";
	}
	public void visualizarFuncionario(){
		this.ver= true;
		this.novo= false;
		this.editar= false;
	}
	
	public void habilitarEdicao(){
		this.editar= true;
		this.novo= false;
	}
	
	public Grupo[] getGrupos() {
		return grupos.values();
	}
	public void getNow(){
		this.funcionario.setUltimoAcesso(new GregorianCalendar().getTime());
	}
	public List<Funcionario> getFuncionarios() {
		dao.open();
		dao.begin();
		funcionarios= dao.findAll();
		dao.close();
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
	

	public void setGrupos(Grupo grupos) {
		this.grupos = grupos;
	}
	public boolean isVer() {
		return ver;
	}
	public void setVer(boolean ver) {
		this.ver = ver;
	}
	public void limparFuncionario(){
		resetFuncionario();
		resetTelefone();
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
	public void resetFuncionario(){
		this.funcionario= new Funcionario();
	}
	
	@Override
	public String toString() {
		return "FuncionarioMB [funcionario=" + funcionario + ", novo=" + novo
				+ ", editar=" + editar + "]";
	}
	
	
	
}
