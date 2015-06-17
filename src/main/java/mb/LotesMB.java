package mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.eclipse.persistence.annotations.Property;

import dao.DAOLote;
import model.Morador;
import model.Lote;

@ManagedBean
@ViewScoped
public class LotesMB extends AbstractMB {
	private List<Lote> lotes;
	private List<Lote> lotesFiltrado;
	private List<Morador> moradores;
	
	private Lote lote;
	private DAOLote dao= new DAOLote();
	
	
	public LotesMB() {
		this.lotes= new ArrayList<Lote>();
		this.moradores= new ArrayList<Morador>();
		
		
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
	public String salvarLote(){
		dao.open();
		dao.begin();
		dao.persist(lote);
		dao.commit();
		dao.close();
//		closeDialog();
		
		resetLote();
		return "lotes";
		
	}
	public void resetLote(){
		this.lote= new Lote();
	}
	public String novoLote(){
		resetLote();
		return "novo-lote";
				
	}
}
