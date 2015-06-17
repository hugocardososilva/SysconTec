package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
@Entity
public class ServicoResidencia extends Servico {
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Pessoa pessoa;
	
	public ServicoResidencia() {
		super();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	
}
