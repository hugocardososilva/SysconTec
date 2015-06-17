package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Ocorrencia {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.DATE)
	private Date dataOcorrencia;
	@Temporal(TemporalType.DATE)
	private Date horaOcorrencia;
	private String descricao;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Usuario usuario;
	
	
	public Ocorrencia() {
		super();
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	public Date getHoraOcorrencia() {
		return horaOcorrencia;
	}
	public void setHoraOcorrencia(Date horaOcorrencia) {
		this.horaOcorrencia = horaOcorrencia;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
