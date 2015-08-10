package mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAO;
import dao.DAOPrestadorPessoa;
import dao.DAOServicoResidencia;
import model.OcorrenciaPrestadorEnum;
import model.OcorrenciaPrestadorServico;
import model.Pessoa;
import model.ServicoResidencia;


@ManagedBean
@ViewScoped
public class OcorrenciaPrestadorMB extends AbstractMB{
	
	private OcorrenciaPrestadorServico ocorrencia;
	private ServicoResidencia servico;
	private OcorrenciaPrestadorEnum ocorrenciaEnum;
	private Pessoa prestador;
	private String senha;
	private DAOPrestadorPessoa daop= new DAOPrestadorPessoa();
	private DAOServicoResidencia daors= new DAOServicoResidencia();
	private DAO dao = new DAO();
	
	
	private boolean iniciarOcorrencia;
	private boolean motivo1;
	private boolean motivo2;
	private boolean motivo3;
	private boolean motivo4;
	private boolean motivo5;
	private boolean motivo6;
	
	
	
	
	public OcorrenciaPrestadorMB() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init(){
		iniciarOcorrencia= false;
		motivo1= false;
		motivo2= false;
		motivo3= false;
		motivo4= false;
		motivo5= false;
		motivo6= false;
		
	}
	public void iniciarOcorrencia(){
		dao.open();
		dao.begin();
		prestador= daop.findBySenha(senha);
		dao.close();
		this.iniciarOcorrencia= true;
			if(prestador != null){
				if(!prestador.isBloqueado()){
				System.out.println("prestador:  "+ prestador.toString());
				
				
				
				
				
				}else{
					displayErrorMessageToUser("Prestador bloqueado!");
				}
			}
			else{
				displayErrorMessageToUser("Prestador não existe!");
				System.out.println("prestador nao existe");
				this.iniciarOcorrencia= false;
			}
		
	}
	
	
	
	
	
	
	public OcorrenciaPrestadorServico getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(OcorrenciaPrestadorServico ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	public ServicoResidencia getServico() {
		return servico;
	}
	public void setServico(ServicoResidencia servico) {
		this.servico = servico;
	}
	public OcorrenciaPrestadorEnum[] getOcorrenciaEnum() {
		return ocorrenciaEnum.values();
	}
	
	public boolean isIniciarOcorrencia() {
		return iniciarOcorrencia;
	}
	public void setIniciarOcorrencia(boolean iniciarOcorrencia) {
		this.iniciarOcorrencia = iniciarOcorrencia;
	}
	public Pessoa getPrestador() {
		return prestador;
	}
	public void setPrestador(Pessoa prestador) {
		this.prestador = prestador;
	}
	public boolean isMotivo1() {
		return motivo1;
	}
	public void setMotivo1(boolean motivo1) {
		this.motivo1 = motivo1;
	}
	public boolean isMotivo2() {
		return motivo2;
	}
	public void setMotivo2(boolean motivo2) {
		this.motivo2 = motivo2;
	}
	public boolean isMotivo3() {
		return motivo3;
	}
	public void setMotivo3(boolean motivo3) {
		this.motivo3 = motivo3;
	}
	public boolean isMotivo4() {
		return motivo4;
	}
	public void setMotivo4(boolean motivo4) {
		this.motivo4 = motivo4;
	}
	public boolean isMotivo5() {
		return motivo5;
	}
	public void setMotivo5(boolean motivo5) {
		this.motivo5 = motivo5;
	}
	public boolean isMotivo6() {
		return motivo6;
	}
	public void setMotivo6(boolean motivo6) {
		this.motivo6 = motivo6;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	
	
	
}
