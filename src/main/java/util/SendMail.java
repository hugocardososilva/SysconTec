package util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import dao.DAOCondominio;
import model.Condominio;
import model.RequisicaoDeSenha;
import model.Usuario;

public class SendMail {
	private DAOCondominio daoc= new DAOCondominio();
	private Condominio condominio;
	private HtmlEmail email;
	
	public SendMail() {
		getCondominio();
		email= new HtmlEmail();
		email.setSSLOnConnect(true);
		email.setHostName(condominio.getHostSmtp());
		email.setSslSmtpPort(condominio.getPortaSmtp());
		email.setAuthenticator(new DefaultAuthenticator(condominio.getEmailDeEnvio().trim(), condominio.getSenhaEmailEnvio().trim()));
		
	}
	public void enviarEmailEsqueciSenha(Usuario usuario, RequisicaoDeSenha requisicao ,HttpServletRequest req){
		daoc.open();
		daoc.begin();
		
		
		
		try {
			System.out.println("enviando email!");
			email.setFrom(condominio.getEmailDeEnvio());
			email.setDebug(true);
			email.setSubject("Redefinição de senha - "+ condominio.getNome());
			email.addTo(requisicao.getEmail());
			
			StringBuffer link= new StringBuffer();
			link.append(req.getScheme());
			link.append("://");
			link.append(req.getServerName());
			link.append(":");
			link.append(req.getServerPort());
			
			link.append(req.getContextPath());
			link.append("/");
			link.append("public/redefinir-senha.xhtml?");
			link.append("h="+requisicao.getHash());
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("<h4>Redefinição de senha - "+ condominio.getNome());
			sb.append("<p> Olá, "+usuario.getNome()+"</p>");
			sb.append("<p> Para alterar sua senha, clique no hash: "+requisicao.getHash() +"</p>");
			sb.append("<p> Para alterar sua senha, clique no link: </p>");
			sb.append("<a href=\""+link.toString()+"\">"+link.toString()+"</a>");
			
			email.setHtmlMsg(sb.toString());
			email.addTo(requisicao.getEmail(), usuario.getNome());
			email.send();
			
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}
	
	public void enviarEmailValidarCadastro(Usuario usuario, RequisicaoDeSenha requisicao ,HttpServletRequest req){
		daoc.open();
		daoc.begin();
		
		
		
		try {
			System.out.println("enviando email!");
			email.setFrom(condominio.getEmailDeEnvio());
			email.setDebug(true);
			email.setSubject("Redefinição de senha - "+ condominio.getNome());
			email.addTo(requisicao.getEmail());
			
			StringBuffer link= new StringBuffer();
			link.append(req.getScheme());
			link.append("://");
			link.append(req.getServerName());
			link.append(":");
			link.append(req.getServerPort());
			
			link.append(req.getContextPath());
			link.append("/");
			link.append("public/validar-cadastro.xhtml?");
			link.append("v="+requisicao.getHash());
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("<h4>Bem vindo! - "+ condominio.getNome());
			sb.append("<p> Olá, "+usuario.getNome()+"</p>");
//			sb.append("<p> Para alterar sua senha, clique no hash: "+requisicao.getHash() +"</p>");
			sb.append("<p> Para concluir o cadastro, clique no link: </p>");
			sb.append("<a href=\""+link.toString()+"\">"+link.toString()+"</a>");
			
			email.setHtmlMsg(sb.toString());
			email.addTo(requisicao.getEmail(), usuario.getNome());
			email.send();
			
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}

	public DAOCondominio getDaoc() {
		return daoc;
	}


	public void setDaoc(DAOCondominio daoc) {
		this.daoc = daoc;
	}


	public Condominio getCondominio() {
		daoc.open();
		daoc.begin();
		condominio= daoc.find(1L);
		return condominio;
	}


	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	
	
	

}
