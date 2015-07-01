package model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	private String login;
	private String senha;
	private String nome;
	private String sobrenome;
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private String cpf;
	private String estadoCivil;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Temporal(TemporalType.DATE)
	private Date ultimoAcesso;
	
	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	private List<Telefone> telefones;
	
	
	public Usuario() {
		this.telefones= new ArrayList<Telefone>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
		try {
			this.senha = this.convertPasswordToMD5(senha);
		} catch (NoSuchAlgorithmException e) {
		
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}
	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public void addTelefone(Telefone telefone){
		this.telefones.add(telefone);
	}
	public void removeTelefone( Telefone telefone){
		this.telefones.remove(telefone);
	}
	
	 public static String convertPasswordToMD5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	 
	        BigInteger hash = new BigInteger(1, md.digest(password.getBytes("UTF-8")));
	 
	        return String.format("%32x", hash);
	    }

}
