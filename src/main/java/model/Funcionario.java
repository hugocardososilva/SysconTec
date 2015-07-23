package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Funcionario extends Usuario implements Serializable{
	private Grupo grupo;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Informacao informacao;
	
	
	public Funcionario() {
		super();
		this.informacao= new Informacao();
	
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Informacao getInformacao() {
		return informacao;
	}

	public void setInformacao(Informacao informacao) {
		this.informacao = informacao;
	}

	@Override
	public String toString() {
		return "Funcionario [grupo=" + grupo + ", informacao=" + informacao
				+ "]";
	}
	
	

}
