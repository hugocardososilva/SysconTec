package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Funcionario extends Usuario {
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Informacao informacao;

	public Funcionario() {
		super();
		this.informacao= new Informacao();
	
	}

	public Informacao getInformacao() {
		return informacao;
	}

	public void setInformacao(Informacao informacao) {
		this.informacao = informacao;
	}
	
	

}
