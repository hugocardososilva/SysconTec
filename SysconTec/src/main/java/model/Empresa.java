package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Empresa extends Prestador{
	
	private String nomeFantasia;
	private long cnpj;
	private long inscricaoEstadual;
	private String razaoSocial;
	private String nomeContato;
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Servico> servicos;
	
	public Empresa() {
		super();
		this.servicos= new ArrayList<Servico>();
		
	}


	public String getNomeFantasia() {
		return nomeFantasia;
	}


	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}


	public long getCnpj() {
		return cnpj;
	}


	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}


	public long getInscricaoEstadual() {
		return inscricaoEstadual;
	}


	public void setInscricaoEstadual(long inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	public String getNomeContato() {
		return nomeContato;
	}


	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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

}
