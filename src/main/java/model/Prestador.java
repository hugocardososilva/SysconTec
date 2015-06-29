package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Prestador {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Informacao informacao;
	
//	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	private List<TipoServico> tipos;
	@ManyToOne
	private TipoServico tipoServico;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Telefone> telefones;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<OcorrenciaPrestadorServico> ocorrencias;
//	private List<Servico> servicos;
	
	public Prestador() {
//		this.tipos= new ArrayList<TipoServico>();
		this.telefones= new ArrayList<Telefone>();
		this.ocorrencias= new ArrayList<OcorrenciaPrestadorServico>();
//		this.servicos= new ArrayList<Servico>();
		this.informacao= new Informacao();
		this.tipoServico= new TipoServico();
	}

	public long getId() {
		return id;
	}
	
//	public List<Servico> getServicos() {
//		return servicos;
//	}
//
//	public void setServicos(List<Servico> servicos) {
//		this.servicos = servicos;
//	}

	public void setId(long id) {
		this.id = id;
	}

//	public List<TipoServico> getTipos() {
//		return tipos;
//	}
//
//	public void setTipos(List<TipoServico> tipos) {
//		this.tipos = tipos;
//	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Informacao getInformacao() {
		return informacao;
	}

	public void setInformacao(Informacao informacao) {
		this.informacao = informacao;
	}

	public List<OcorrenciaPrestadorServico> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<OcorrenciaPrestadorServico> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public void addTelefone(Telefone telefone){
		this.telefones.add(telefone);
	}
	public void removeTelefone(Telefone telefone){
		this.telefones.remove(telefone);
	}
	
//	public void addTipo(TipoServico ts){
//		this.tipos.add(ts);
//	}
//	public void removeTipo(TipoServico ts){
//		this.tipos.remove(ts);
//	}
	public void addOcorrencia(OcorrenciaPrestadorServico ops){
		this.ocorrencias.add(ops);
	}
	public void removeOcorrencia(OcorrenciaPrestadorServico ops){
		this.ocorrencias.remove(ops);
	}
//	public void addServico(Servico servico){
//		this.servicos.add(servico);
//	}
//	public void removeServico(Servico servico){
//		this.servicos.remove(servico);
//	}

	@Override
	public String toString() {
		return "Prestador [id=" + id + ", informacao=" + informacao
				+ ", tipoServico=" + tipoServico + ", telefones=" + telefones
				+ ", ocorrencias=" + ocorrencias + "]";
	}
	
}
