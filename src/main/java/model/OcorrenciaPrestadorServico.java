package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
@Entity
public class OcorrenciaPrestadorServico extends Ocorrencia {
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Prestador prestador;
	
	public OcorrenciaPrestadorServico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}
	
	

}
