package mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;





import org.joda.time.DateTime;

import dao.DAO;
import dao.DAOOcorrenciaPrestador;
import dao.DAOPrestadorPessoa;
import dao.DAOServicoResidencia;
import model.OcorrenciaPrestadorEnum;
import model.OcorrenciaPrestadorServico;
import model.Pessoa;
import model.ServicoResidencia;


@ManagedBean
@ViewScoped
public class OcorrenciaPrestadorMB extends AbstractMB implements Serializable{
	
	private OcorrenciaPrestadorServico ocorrencia;
	private ServicoResidencia servico;
	private OcorrenciaPrestadorEnum ocorrenciaEnum;
	private Pessoa prestador;
	private String senha;
	private DAOPrestadorPessoa daop= new DAOPrestadorPessoa();
	private DAOServicoResidencia daors= new DAOServicoResidencia();
	private DAO dao = new DAO();
	private DAOOcorrenciaPrestador daoo= new DAOOcorrenciaPrestador();
	private List<ServicoResidencia> servicosAbertos= new ArrayList<ServicoResidencia>();
	
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
		ocorrencia= new OcorrenciaPrestadorServico();
		servico= new ServicoResidencia();
		
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
				System.out.println(ocorrencia.getTipo());
				
				selecionarMotivo();
				
				
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
	
	public void selecionarMotivo(){
		if(ocorrencia.getTipo()== OcorrenciaPrestadorEnum.CHEGAR_CEDO_1){
			motivo1= true;
			motivo2= false;
			motivo3= false;
			motivo4= false;
			motivo5= false;
			motivo6= false;
			servico= new ServicoResidencia();
			ocorrencia.setDataOcorrencia(new Date(System.currentTimeMillis()));
			ocorrencia.setHoraOcorrencia(new Date(System.currentTimeMillis()));
			
			
		}else if(ocorrencia.getTipo() == OcorrenciaPrestadorEnum.SAIR_CEDO_2){
			motivo1= false;
			motivo2= true;
			motivo3= false;
			motivo4= false;
			motivo5= false;
			motivo6= false;
			ocorrencia.setDataOcorrencia(new Date(System.currentTimeMillis()));
			ocorrencia.setHoraOcorrencia(new Date(System.currentTimeMillis()));
			
			
		}else if(ocorrencia.getTipo()== OcorrenciaPrestadorEnum.CHEGAR_ATRASADO_3){
			motivo1= false;
			motivo2= false;
			motivo3= true;
			motivo4= false;
			motivo5= false;
			motivo6= false;
			ocorrencia.setDataOcorrencia(new Date(System.currentTimeMillis()));
			ocorrencia.setHoraOcorrencia(new Date(System.currentTimeMillis()));
			
			
			
		}else if (ocorrencia.getTipo() == OcorrenciaPrestadorEnum.SAIR_ATRASADO_4){
			motivo1= false;
			motivo2= false;
			motivo3= false;
			motivo4= true;
			motivo5= false;
			motivo6= false;
			ocorrencia.setDataOcorrencia(new Date(System.currentTimeMillis()));
			ocorrencia.setHoraOcorrencia(new Date(System.currentTimeMillis()));
			
		
		}else if(ocorrencia.getTipo() == OcorrenciaPrestadorEnum.ESQUECER_PONTO_ENTRADA_5){
			motivo1= false;
			motivo2= false;
			motivo3= false;
			motivo4= false;
			motivo5= true;
			motivo6= false;
			ocorrencia.setDataOcorrencia(new Date(System.currentTimeMillis()));
			ocorrencia.setHoraOcorrencia(new Date(System.currentTimeMillis()));
			
			
		}else if(ocorrencia.getTipo() == OcorrenciaPrestadorEnum.ESQUECER_PONTO_SAIDA_6){
			motivo1= false;
			motivo2= false;
			motivo3= false;
			motivo4= false;
			motivo5= false;
			motivo6= true;
			ocorrencia.setDataOcorrencia(new Date(System.currentTimeMillis()));
			ocorrencia.setHoraOcorrencia(new Date(System.currentTimeMillis()));
			
			
		}
	}
	
	public void registrarMotivo1(){
		
		System.out.println("registrando chegada do prestador mais cedo.");
		
		
		servico.setDataEntrada(new Date(System.currentTimeMillis()));
		servico.setHoraEntrada(new Date(System.currentTimeMillis()));
		DateTime horarioEntradaServico= new DateTime(servico.getHoraEntrada());
		DateTime horarioEntradaPrestador= new DateTime(prestador.getHoraEntrada());
		
		if(horarioEntradaServico.getMinuteOfDay() > horarioEntradaPrestador.getMinuteOfDay() ){
			displayErrorMessageToUser("O horário de entrada do serviço é maior que o horário de entrada do prestador,"
					+ " portanto, ele está atrasado. Altere a configuração da ocorrência para uma apropriada.");
		}else{
				dao.open();
				dao.begin();
			
				servicosAbertos= daors.findServicoEmAbertoByPrestador(prestador);
				
					if(!servicosAbertos.isEmpty()){
						displayErrorMessageToUser("Já existe serviço(s) em aberto, por favor, finalize outro(s) serviços"
								+ " antes de iniciar outro.");
					}else{
			
							
							servico.setPrestador(prestador);
							servico.setConcluido(false);
							prestador.setUltimoAcesso(new Date(System.currentTimeMillis()));
							prestador.addServico(servico);
							ocorrencia.setPrestador(prestador);
							ocorrencia.setServico(servico);
							prestador.addOcorrencia(ocorrencia);
						daop.merge(prestador);
						daoo.persist(ocorrencia);
						daors.persist(servico);
						dao.commit();
						displayInfoMessageToUser("Ocorrência registrada com sucesso!");
						reset();
					}
		}
		reset();
	}
	public void registrarMotivo2(){
		System.out.println("registrando saída do prestador mais cedo.");
			
			servico.setDataSaida(new Date(System.currentTimeMillis()));
			servico.setHoraSaida(new Date(System.currentTimeMillis()));
			DateTime horarioSaidaServico= new DateTime(servico.getHoraSaida());
			DateTime horarioSaidaPrestador= new DateTime(prestador.getHoraSaida());
			
			if(horarioSaidaPrestador.getMinuteOfDay() < horarioSaidaServico.getMinuteOfDay()){
				displayErrorMessageToUser("O horário de saída do serviço é maior que o horário de saída do prestador, "
						+ "portanto, ele está saindo atrasado. Altere a configuração da ocorrência para uma apropriada.");
			}else{
			
			
			dao.open();
			dao.begin();
			
			
		
			servicosAbertos= daors.findServicoEmAbertoByPrestador(prestador);
				if(servicosAbertos.isEmpty()){
					displayErrorMessageToUser("Não existe nenhum serviço em aberto, por favor, registre a entrada "
							+ "antes de finalizar um serviço.");
				}else{
					Calendar datahj= Calendar.getInstance();
					for(ServicoResidencia sr: servicosAbertos){
						Calendar dataEntrada= Calendar.getInstance();
						dataEntrada.setTime(sr.getDataEntrada());
						if(dataEntrada.DATE== datahj.DATE ){
							servico= sr;
						}
					}
					
					servico.setDataSaida(new Date(System.currentTimeMillis()));
					servico.setHoraSaida(new Date(System.currentTimeMillis()));
					servico.setConcluido(true);
					ocorrencia.setServico(servico);
					ocorrencia.setPrestador(prestador);
					prestador.addOcorrencia(ocorrencia);
					daoo.persist(ocorrencia);
					daors.merge(servico);
					daop.merge(prestador);
					dao.commit();
					displayInfoMessageToUser("Ocorrência registrada com sucesso! ");
					reset();
				}
			}
			reset();
	}
	public void registrarMotivo3(){
		System.out.println("registrando chegada do prestador atrasado.");
		dao.open();
		dao.begin();
		
			servicosAbertos= daors.findServicoEmAbertoByPrestador(prestador);
			
				if(!servicosAbertos.isEmpty()){
					displayErrorMessageToUser("Já existe serviço(s) em aberto, por favor, finalize outro(s) serviços"
							+ " antes de iniciar outro.");
				}else{
		
						servico.setDataEntrada(new Date(System.currentTimeMillis()));
						servico.setHoraEntrada(new Date(System.currentTimeMillis()));
						servico.setPrestador(prestador);
						servico.setConcluido(false);
						prestador.setUltimoAcesso(new Date(System.currentTimeMillis()));
						prestador.addServico(servico);
						ocorrencia.setPrestador(prestador);
						ocorrencia.setServico(servico);
						prestador.addOcorrencia(ocorrencia);
					daop.merge(prestador);
					daoo.persist(ocorrencia);
					daors.persist(servico);
					dao.commit();
					displayInfoMessageToUser("Ocorrência registrada com sucesso!");
					reset();
				}
		
	}
	public void registrarMotivo4(){
		System.out.println("registrando saída do prestador mais tarde.");
		dao.open();
		dao.begin();
	
		servicosAbertos= daors.findServicoEmAbertoByPrestador(prestador);
			if(servicosAbertos.isEmpty()){
				displayErrorMessageToUser("Não existe nenhum serviço em aberto, por favor, registre a entrada "
						+ "antes de finalizar um serviço.");
			}else{
				Calendar datahj= Calendar.getInstance();
				for(ServicoResidencia sr: servicosAbertos){
					Calendar dataEntrada= Calendar.getInstance();
					dataEntrada.setTime(sr.getDataEntrada());
					if(dataEntrada.DATE== datahj.DATE ){
						servico= sr;
					}
				}
				
				servico.setDataSaida(new Date(System.currentTimeMillis()));
				servico.setHoraSaida(new Date(System.currentTimeMillis()));
				servico.setConcluido(true);
				ocorrencia.setServico(servico);
				ocorrencia.setPrestador(prestador);
				prestador.addOcorrencia(ocorrencia);
				daoo.persist(ocorrencia);
				daop.merge(prestador);
				daors.merge(servico);
				dao.commit();
				displayInfoMessageToUser("Ocorrência registrada com sucesso! ");
				reset();
			}
		
	}
	public void registrarMotivo5(){
		System.out.println("registrando entrada de prestador que esqueceu de registrar o ponto.");
		dao.open();
		dao.begin();
		
//		servicosAbertos= daors.findServicoEmAbertoByPrestador(prestador);
		
		List<ServicoResidencia> servicosPrestador= new ArrayList<ServicoResidencia>();
		servicosPrestador= daors.findServicoByPrestadorData(prestador, servico.getDataEntrada());
		if(!servicosPrestador.isEmpty()){
			displayErrorMessageToUser("Já existe um serviço iniciado nessa data, por favor, tente outra data "
					+ "ou tente outro motivo para essa ocorrência. ");
		}else{
		
//		Calendar dataServico= Calendar.getInstance();
//		dataServico.setTime(servico.getDataEntrada());
//			for(ServicoResidencia sr: servicosAbertos){
//				Calendar dataEntrada = Calendar.getInstance();
//				dataEntrada.setTime(sr.getDataEntrada());
//				if(dataEntrada.DATE == dataServico.DATE){
//					displayErrorMessageToUser("Já existe um serviço iniciado nessa data, por favor, tente outra data "
//							+ "ou tente outro motivo para essa ocorrência. ");
//				}
//			}
//		
		servico.setConcluido(false);
		servico.setPrestador(prestador);
		ocorrencia.setServico(servico);
		ocorrencia.setPrestador(prestador);
		prestador.addOcorrencia(ocorrencia);
		daoo.persist(ocorrencia);
		daors.persist(servico);
		daop.merge(prestador);
		
		displayInfoMessageToUser("Ocorrência registrada com sucesso! ");
		}
		dao.commit();
	}
	public void registrarMotivo6(){
		System.out.println("registrando saída de prestador que esqueceu de registrar o ponto.");
		dao.open();
		dao.begin();
		
		servicosAbertos= daors.findServicoEmAbertoByPrestador(prestador);
		Calendar dataServico= Calendar.getInstance();
		dataServico.setTime(servico.getDataSaida());
			for(ServicoResidencia sr: servicosAbertos){
				Calendar dataSaida = Calendar.getInstance();
				dataSaida.setTime(sr.getDataEntrada());
				if(dataSaida.DATE == dataServico.DATE){
					sr.setDataSaida(servico.getDataSaida());
					sr.setHoraSaida(servico.getHoraSaida());
					sr.setConcluido(true);
					ocorrencia.setPrestador(prestador);
					ocorrencia.setServico(sr);
					daors.merge(sr);
					daoo.persist(ocorrencia);
					daop.merge(prestador);
					displayInfoMessageToUser("Ocorrência registrada com sucesso!");
					
				}
				
			}
			dao.commit();
		
	}
	
	
	public void reset(){
		iniciarOcorrencia= false;
		motivo1= false;
		motivo2= false;
		motivo3= false;
		motivo4= false;
		motivo5= false;
		motivo6= false;
		ocorrencia= new OcorrenciaPrestadorServico();
		servico= new ServicoResidencia();
		servicosAbertos= new ArrayList<ServicoResidencia>();
		senha= null;
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
