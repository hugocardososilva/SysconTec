package mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.TipoServico;

@ManagedBean
@ViewScoped
public class TipoServicoMB extends AbstractMB{
	private TipoServico tipoServico;
	private List<TipoServico> tiposServico = new ArrayList<TipoServico>();
	
	public TipoServicoMB() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	private void init(){
		this.tipoServico= new TipoServico();
	}

}
