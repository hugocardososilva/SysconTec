package mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAO;
import dao.DAOTipoServico;
import model.TipoServico;

@ManagedBean
@ViewScoped
public class TipoServicoMB extends AbstractMB implements Serializable{
	private TipoServico tipoServico;
	private List<TipoServico> tiposServico = new ArrayList<TipoServico>();
	private DAO dao= new DAO();
	private DAOTipoServico daot= new DAOTipoServico();
	private boolean editar;
	private boolean novo;
	
	public TipoServicoMB() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	private void init(){
		this.tipoServico= new TipoServico();
		this.tiposServico= new ArrayList<TipoServico>();
		this.novo= false;
		this.editar= false;
		
	}
	
	public void adicionarTipoServico(){
		dao.open();
		dao.begin();
			daot.persist(tipoServico);
		dao.commit();
		displayInfoMessageToUser("Tipo de serviço adicionado com sucesso!");
	}
	public void novoTipoServico(){
		this.editar= false;
		this.novo= true;
		resetTipoServico();
	}
	public void habilitarEdicao(){
		this.editar= true;
		this.novo= false;
	}
	
	
	
	public void resetTipoServico(){
		this.tipoServico= new TipoServico();
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public List<TipoServico> getTiposServico() {
		dao.open();
		dao.begin();
		tiposServico=daot.findAll();
		dao.close();
		return tiposServico;
	}
	public void salvarTipoServico(){
		daot.open();
		daot.begin();
		daot.persist(tipoServico);
		dao.commit();
		getTiposServico();
		resetTipoServico();
		this.novo= false;
		this.editar= false;
		displayInfoMessageToUser("Tipo de Serviço adicionado com sucesso!");
	}
	public String editarTipoServico(){
		daot.open();
		daot.begin();
		daot.merge(tipoServico);
		dao.commit();
		displayInfoMessageToUser("Tipo de serviço adicionado com sucesso!");
		return "gerenciar-tipos-servico";
	}
	public void removerTipoServico(){
		dao.open();
		dao.begin();
		tipoServico= daot.merge(tipoServico);
		daot.remove(tipoServico);
		daot.commit();
		novo=false;
		editar=false;
		resetTipoServico();
	}
	
	
	
	
	
	
	

	public void setTiposServico(List<TipoServico> tiposServico) {
		this.tiposServico = tiposServico;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}
	
	
}
