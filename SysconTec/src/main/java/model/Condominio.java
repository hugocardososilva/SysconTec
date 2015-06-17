package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Condominio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int diasSemUtilizarSistema;
	private int numeroMaximoMoradores;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Informacao informacao;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Telefone> telefones;
	
	
	public Condominio() {
		super();
		this.telefones= new ArrayList<Telefone>();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	
	public List<Telefone> getTelefones() {
		return telefones;
	}


	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}


	public Informacao getInformacao() {
		return informacao;
	}


	public void setInformacao(Informacao informacao) {
		this.informacao = informacao;
	}


	public int getDiasSemUtilizarSistema() {
		return diasSemUtilizarSistema;
	}


	public void setDiasSemUtilizarSistema(int diasSemUtilizarSistema) {
		this.diasSemUtilizarSistema = diasSemUtilizarSistema;
	}


	public int getNumeroMaximoMoradores() {
		return numeroMaximoMoradores;
	}


	public void setNumeroMaximoMoradores(int numeroMaximoMoradores) {
		this.numeroMaximoMoradores = numeroMaximoMoradores;
	}
	
	public void addTelefone(Telefone telefone){
		this.telefones.add(telefone);
	}
	public void removeTelefone(Telefone telefone){
		this.telefones.remove(telefone);
	}
	
	

}
