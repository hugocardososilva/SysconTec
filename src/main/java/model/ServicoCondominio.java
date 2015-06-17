package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ServicoCondominio extends Servico{
	@ManyToOne
	private Prestador prestador; 
	
	public ServicoCondominio() {
		super();
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}
	
	
}
