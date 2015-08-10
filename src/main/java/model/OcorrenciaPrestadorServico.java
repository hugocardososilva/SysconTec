package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
@Entity
public class OcorrenciaPrestadorServico extends Ocorrencia {
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	private Prestador prestador;
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	private ServicoResidencia servico;
	
	private OcorrenciaPrestadorEnum tipo;
	
	
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

	public ServicoResidencia getServico() {
		return servico;
	}

	public void setServico(ServicoResidencia servico) {
		this.servico = servico;
	}

	public OcorrenciaPrestadorEnum getTipo() {
		return tipo;
	}

	public void setTipo(OcorrenciaPrestadorEnum tipo) {
		this.tipo = tipo;
	}
	
	

}
