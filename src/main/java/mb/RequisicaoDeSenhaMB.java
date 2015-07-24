package mb;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.calendar.Calendar;

import util.SendMail;
import dao.DAO;
import dao.DAORequisicaoDeSenha;
import dao.DAOUser;
import model.RequisicaoDeSenha;
import model.Usuario;

@ManagedBean
@RequestScoped
public class RequisicaoDeSenhaMB extends AbstractMB {
	private List<RequisicaoDeSenha> requisicoes = new ArrayList<RequisicaoDeSenha>();
	private Usuario usuario;
	private String email;
	private RequisicaoDeSenha requisicao;
	private DAOUser daou= new DAOUser();
	private DAO dao = new DAO();
	private DAORequisicaoDeSenha daors= new DAORequisicaoDeSenha();
	private SendMail envioEmail;
	private String senha;
	private String hash;
	
	public RequisicaoDeSenhaMB() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init(){
		this.requisicao= new RequisicaoDeSenha();
		
	}
	public void enviarEmail(){
		HttpServletRequest request = 
				(HttpServletRequest)FacesContext
					.getCurrentInstance()
						.getExternalContext()
							.getRequest();
		dao.open();
		dao.begin();
		try {
			
			requisicoes= daors.findByEmail(email);
			for(RequisicaoDeSenha r : requisicoes){
				r.setValido(false);
				daors.merge(r);
			}
			
			
			
			
			usuario= daou.getByEmail(email);
			requisicao.setUsuario(usuario);
			requisicao.setEmail(usuario.getEmail());
			requisicao.setHash(gerarHash());
			requisicao.setDataRequisicao(new Date());
			requisicao.setValido(true);
			
			daors.persist(requisicao);
			
			dao.commit();
			System.out.println("testando email:");
			System.out.println("email enviado para: "+requisicao.getEmail());
			System.out.println("hash: "+ requisicao.getHash());
			envioEmail= new SendMail();
			envioEmail.enviarEmailEsqueciSenha(usuario, requisicao ,request);
			
			
		} catch (NoResultException e) {
			displayErrorMessageToUser("Email não encontrado.");
		}catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			dao.close();
		}
		
		dao.close();
		
		
	}
	public String redefinirSenha(){
		
		try {
			dao.open();
			dao.begin();
			requisicao= daors.findByHash(hash);
		if(requisicao.isValido()){
			usuario= daou.getByEmail(requisicao.getEmail());
			
			usuario.setSenha(senha);
			requisicao.setValido(false);
			daou.merge(usuario);
			daors.merge(requisicao);
			
			dao.commit();
			displayInfoMessageToUser("Senha alterada com sucesso!");
		}else{
			displayErrorMessageToUser("Essa requisição não é valida, por favor, faça novamente a requisição de redefinir senha e clique no link enviado por email");
		}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.close();
		}
		
		
		return "/index?faces-redirect=true";
	}
	public static String gerarHash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        long i = (long) (Math.random() * 1000000000000000000l);
        BigInteger hash = new BigInteger(1, md.digest(String.valueOf(i).getBytes("UTF-8")));
 
        return String.format("%32x", hash);
    }
	public List<RequisicaoDeSenha> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<RequisicaoDeSenha> requisicoes) {
		this.requisicoes = requisicoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public RequisicaoDeSenha getRequisicao() {
		return requisicao;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setRequisicao(RequisicaoDeSenha requisicao) {
		this.requisicao = requisicao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
