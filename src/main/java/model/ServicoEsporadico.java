package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ServicoEsporadico extends Servico{
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Prestador prestador; 
	@ManyToOne(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	private Lote lote;
	
	public ServicoEsporadico() {
		super();
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	
	
}
