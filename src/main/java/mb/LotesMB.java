package mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.eclipse.persistence.annotations.Property;

import dao.DAOLote;
import dao.DAOMorador;
import model.Morador;
import model.Lote;
import model.Pessoa;

@ManagedBean
@ViewScoped
public class LotesMB extends AbstractMB implements Serializable {
	private List<Lote> lotes;
	private List<Lote> lotesFiltrado;
	private List<Morador> moradores;
	
	
	private Lote lote;
	private DAOLote dao= new DAOLote();
	private DAOMorador daom= new DAOMorador();
	
	private boolean editar;
	private boolean novo;
	
	
	public LotesMB() {
		
		
	}
	
	@PostConstruct
	public void init(){
		this.lotes= new ArrayList<Lote>();
		this.moradores= new ArrayList<Morador>();
		this.novo= false;
		this.editar= false;
		
	}
	
	public void salvarLote(){
		lote.setMoradores(null);
		lote.setPessoas(null);
		lote.getResponsavel().addResponsabilidade(lote);
		lote.getResponsavel().setResponsavel(true);
		
		dao.open();
		dao.begin();
		dao.persist(lote);
		dao.commit();
		getLotes();
		resetLote();
		this.novo= false;
		this.editar= false;
		displayInfoMessageToUser("Lote adicionado com sucesso!");
	}
	
	public void resetLote(){
		this.lote= new Lote();
	}
	
	public void novoLote(){
		resetLote();
		this.editar= false;
		this.novo= true;
		
				
	}
	public void removerLote(){
		System.out.println("removendo lote: " + lote.toString());
	
			
			System.out.println("verificando ligações");
			
			
			dao.open();
			dao.begin();
			lote= dao.merge(lote);
			
			
			
			dao.remove(lote);
			dao.commit();
			System.out.println("lote removido");
			novo= false;
			editar= false;
			resetLote();
			
	
		
		
	}
	public void habilitarEdicao(){
		this.editar= true;
		this.novo= false;
	}
	public String editarLote(){
		lote.getResponsavel().addResponsabilidade(lote);
		lote.getResponsavel().setResponsavel(true);
		dao.open();
		dao.begin();
		dao.merge(lote);
		dao.commit();
		displayInfoMessageToUser("Lote editado com sucesso!");
		return "gerenciar-lotes";
	}

	public List<Lote> getLotes() {
		dao.open();
		dao.begin();
		lotes= dao.findAll();
		dao.close();
		return lotes;
	}
	
	public Lote getLote() {
		if(lote== null){
			lote=  new Lote();
		}
		return lote;
	}
	
	
	public List<Morador> getMoradores() {
		dao.open();
		dao.begin();
		moradores= daom.findAll();
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
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

	public List<Morador> getInquilinos() {
		return moradores;
	}
	

	public void setInquilinos(List<Morador> moradores) {
		this.moradores = moradores;
	}

	public List<Lote> getLotesFiltrado() {
		return lotesFiltrado;
	}

	public void setLotesFiltrado(List<Lote> lotesFiltrado) {
		this.lotesFiltrado = lotesFiltrado;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}
	
	public String redirectLotes(){
		resetLote();
		return "lotes.xhtml?faces-redirect=true";
	}

}
