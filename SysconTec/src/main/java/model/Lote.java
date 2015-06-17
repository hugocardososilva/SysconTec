package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import converter.GenericInterface;
@Entity
public class Lote implements GenericInterface{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private Long numero;
	private String quadra;
	
	@ManyToOne(cascade= CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Morador responsavel;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="lote",fetch=FetchType.LAZY)
	private List<Morador> moradores;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="lote", fetch=FetchType.LAZY)
	private List<Pessoa> pessoas;
	
	@OneToMany(fetch=FetchType.LAZY)
	@OrderBy("horaEntrada DESC")
	private List<Servico> servicos;
	
	
	public Lote() {
		this.moradores= new ArrayList<Morador>();
		this.pessoas= new ArrayList<Pessoa>();
		this.servicos= new ArrayList<Servico>();
		this.responsavel= new Morador();
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Long getNumero() {
		return numero;
	}


	public void setNumero(Long numero) {
		this.numero = numero;
	}


	

	public String getQuadra() {
		return quadra;
	}


	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}


	public Morador getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(Morador responsavel) {
		this.responsavel = responsavel;
	}


	public List<Morador> getMoradores() {
		return moradores;
	}


	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}


	
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}


	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}


	public void addPessoa(Pessoa p){
		this.pessoas.add(p);
		
	}
	public void removePessoa(Pessoa p){
		this.pessoas.remove(p);
	}

	public List<Servico> getServicos() {
		return servicos;
	}


	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}	
	public void addServico(Servico s){
		this.servicos.add(s);
	}
	public void removeServico(Servico s){
		this.servicos.remove(s);
	}


	@Override
	public String toString() {
		return "Lote [id=" + id + ", numero=" + numero + ", quadra=" + quadra
				+ "]";
	}
	

}
