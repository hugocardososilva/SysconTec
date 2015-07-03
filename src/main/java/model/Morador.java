package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Morador extends Usuario {
	private boolean responsavel;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	private Lote lote;
	
	@OneToMany(mappedBy="responsavel",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Lote> responsabilidade;
	

	public Morador() {
		super();
		this.responsabilidade= new ArrayList<Lote>();
	}

	public boolean isResponsavel() {
		return responsavel;
	}

	public void setResponsavel(boolean responsavel) {
		this.responsavel = responsavel;
	}

	
	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public List<Lote> getResponsabilidade() {
		return responsabilidade;
	}

	public void setResponsabilidade(List<Lote> responsabilidade) {
		this.responsabilidade = responsabilidade;
	}
	
	public void addResponsabilidade(Lote lote){
		this.responsabilidade.add(lote);
	}
	public void removeResposabilidade(Lote lote){
		this.responsabilidade.remove(lote);
	}
	@Override
	public boolean equals(Object other) {
		return (other != null && getClass() == other.getClass() && this.id != null)
	            ? id.equals(((Morador) other).id)
	            : (other == this);
	}
	@Override
	public int hashCode() {
		return (id != null) 
	            ? (getClass().hashCode() + id.hashCode())
	            : super.hashCode();
	}
	

}
