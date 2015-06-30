package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;

import converter.GenericInterface;
@Entity
public class TipoServico implements GenericInterface{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique= true)
	private String tipo;
	private String descricao;
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Prestador> prestadores;
	
	
	public TipoServico() {
		super();
		this.prestadores= new ArrayList<Prestador>();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public List<Prestador> getPrestadores() {
		return prestadores;
	}


	public void setPrestadores(List<Prestador> prestadores) {
		this.prestadores = prestadores;
	}
	
	public void addPrestador(Prestador prestador){
		this.prestadores.add(prestador);
	}
	public void removePrestador(Prestador prestador){
		this.prestadores.remove(prestador);
	}


	@Override
	public String toString() {
		return "TipoServico [id=" + id + ", tipo=" + tipo + ", descricao="
				+ descricao + "]";
	}
	
	

}
