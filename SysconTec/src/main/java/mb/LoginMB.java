package mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFacade;
import model.Funcionario;
import model.Morador;
import model.Usuario;

@ManagedBean
@SessionScoped
public class LoginMB extends AbstractMB {
	
	private Usuario user;
	
	private String login;
	private String senha;
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
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
		
	}
	public String redirectAcesso(){
		FacesContext ctx= FacesContext.getCurrentInstance();
		HttpServletRequest req= (HttpServletRequest) ctx.getExternalContext().getRequest();
		Usuario usuario= (Usuario) req.getSession().getAttribute("user");
		System.out.println("classe do usuario  "+ usuario.getClass());
		if(user.getClass()== Funcionario.class){
			return "/protected/funcionario/index.xhtml?faces-redirect=true";
		
			}else
				if(user.getClass()== Morador.class){
					return "/protected/inquilino/index.xhtml";
				
				}else{
					return "/login.xhtml?faces-redirect=true";
				}
		
	
	}
	
	public String login(){
		System.out.println("logando");
		DAOFacade dao= new DAOFacade();
		Usuario usuario;
		
		try {
			System.out.println(Usuario.convertPasswordToMD5(senha));
			usuario = dao.isValidLogin(login, Usuario.convertPasswordToMD5(senha));
			System.out.println(usuario);
		
		if(usuario!= null){
			user = usuario;
			FacesContext ctx= FacesContext.getCurrentInstance();
			HttpServletRequest req= (HttpServletRequest) ctx.getExternalContext().getRequest();
			req.getSession().setAttribute("user", user);
			System.out.println(user.getClass() +  "  classe do tipo");
			
			if(user.getClass()== Funcionario.class){
				return "/protected/funcionario/index.xhtml?faces-redirect=true";
			
				}else
					if(user.getClass()== Morador.class){
						return "/protected/inquilino/index.xhtml?faces-redirect=true";
					}else{
						return "/login.xhtml?faces-redirect=true";
					}
			
		}
		displayErrorMessageToUser("Usuário ou senha inválidos");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void logout(){
		HttpServletRequest req=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		req.getSession().invalidate();
		HttpServletResponse res= (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		System.out.println("logout");
		try {
			req.getRequestDispatcher("/index.xhtml").forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
