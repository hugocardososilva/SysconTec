package mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Lote;
import model.PessoaEsporadica;
import model.ServicoEsporadico;
import model.TipoServico;

@ManagedBean
@ViewScoped
public class ServicoVisitanteMB extends AbstractMB{

	private ServicoEsporadico servicoEsporadico;
	private PessoaEsporadica pessoaEsporadica;
	private List<ServicoEsporadico> servicosEmAberto;
	private List<Lote> lotes;
	private List<TipoServico> tipos;
	private TipoServico tipo;
	
	public ServicoVisitanteMB() {
		this.servicosEmAberto= new ArrayList<ServicoEsporadico>();
		this.lotes= new ArrayList<Lote>();
		this.tipos= new ArrayList<TipoServico>();
	}
	
	
	public TipoServico getTipo() {
		return tipo;
	}


	public void setTipo(TipoServico tipo) {
		this.tipo = tipo;
	}


	public List<TipoServico> getTipos() {
		return tipos;
	}


	public void setTipos(List<TipoServico> tipos) {
		this.tipos = tipos;
	}


	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}

	public List<ServicoEsporadico> getServicosEmAberto() {
		return servicosEmAberto;
	}

	public void setServicosEmAberto(List<ServicoEsporadico> servicosEmAberto) {
		this.servicosEmAberto = servicosEmAberto;
	}

	public ServicoEsporadico getServicoEsporadico() {
		return servicoEsporadico;
	}
	public void setServicoEsporadico(ServicoEsporadico servicoEsporadico) {
		this.servicoEsporadico = servicoEsporadico;
	}
	public PessoaEsporadica getPessoaEsporadica() {
		return pessoaEsporadica;
	}
	public void setPessoaEsporadica(PessoaEsporadica pessoaEsporadica) {
		this.pessoaEsporadica = pessoaEsporadica;
	}
	
	
	
	public String redirectServicoVisitante(){
		System.out.println("entrando em serviço visita");
		return "movimentacao-visitas-servico";
	}
}
