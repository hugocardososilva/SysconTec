package mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.DAO;
import dao.DAORequisicaoDeSenha;
import dao.DAOUser;
import model.RequisicaoDeSenha;
import model.Usuario;

@ManagedBean
@RequestScoped
public class ValidarCadastroMB extends AbstractMB{
	private String senha;
	private String hash;
	private Usuario usuario;
	private String email;
	private RequisicaoDeSenha requisicao;
	private DAOUser daou= new DAOUser();
	private DAO dao = new DAO();
	private DAORequisicaoDeSenha daors= new DAORequisicaoDeSenha();

	public ValidarCadastroMB() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init(){
		this.requisicao= new RequisicaoDeSenha();
		
	}
	public String validarCadastro(){
		
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
	
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	public void setRequisicao(RequisicaoDeSenha requisicao) {
		this.requisicao = requisicao;
	}

	
	
}
