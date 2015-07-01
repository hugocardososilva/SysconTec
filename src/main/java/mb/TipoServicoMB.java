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
	public TipoServicoMB() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	private void init(){
		this.tipoServico= new TipoServico();
		
	}
	
	public void adicionarTipoServico(){
		dao.open();
		dao.begin();
			daot.persist(tipoServico);
		dao.commit();
		displayInfoMessageToUser("Tipo de serviço adicionado com sucesso!");
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
		return tiposServico;
	}

	public void setTiposServico(List<TipoServico> tiposServico) {
		this.tiposServico = tiposServico;
	}

	
}
