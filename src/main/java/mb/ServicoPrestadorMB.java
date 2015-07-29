package mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import dao.DAO;
import dao.DAOCondominio;
import dao.DAOPrestadorPessoa;
import dao.DAOServicoResidencia;
import model.Condominio;
import model.Pessoa;
import model.Prestador;
import model.ServicoResidencia;

@ManagedBean
@ViewScoped
public class ServicoPrestadorMB extends AbstractMB implements Serializable {
		
		private List<ServicoResidencia> servicosEmAberto;
		private Condominio condominio;
		private DAOPrestadorPessoa daop= new DAOPrestadorPessoa();
		private DAOServicoResidencia daors= new DAOServicoResidencia();
		private DAO dao = new DAO();
		private String senha;
		private Pessoa prestador;
		private ServicoResidencia servico;
		
		private String senhaEntrada;
		
		
		private boolean entrou;
		private boolean saiu;
		private boolean iniciarServico;
		Calendar dataHj = Calendar.getInstance();
		Calendar horaEntrada= Calendar.getInstance();
		Calendar horaSaida= Calendar.getInstance();
		
		
		
		public ServicoPrestadorMB() {
			dataHj.setTime(new Date(System.currentTimeMillis()));	
			
		}
		@PostConstruct
		public void init(){
			
			this.saiu= false;
			this.iniciarServico= false;
			this.servico = new ServicoResidencia();
			this.prestador= new Pessoa();
			this.servicosEmAberto= new ArrayList<ServicoResidencia>();
			getServicosEmAberto();
		}

		
		public void keyPressSenha(){
			System.out.println("digitou");
			
		
				dao.open();
				dao.begin();
				prestador= daop.findBySenha(senha);
				this.iniciarServico= true;
					if(prestador != null){
						if(!prestador.isBloqueado()){
						System.out.println("prestador:  "+ prestador.toString());
						
							//servico de residencia
//							if(prestador.getLote() != null){
								registrarPrestadorLote();										
						
//							}
						}else{
							displayErrorMessageToUser("Prestador bloqueado!");
						}
					}
					else{
						displayErrorMessageToUser("Prestador não existe!");
						System.out.println("prestador nao existe");
						this.iniciarServico= false;
					}
				
				
					
		
		}
		public void registrarPrestadorLote(){
			//verificando se há algum servico em aberto
			System.out.println("verificando algum servico em aberto");
			List<ServicoResidencia> servicos= new ArrayList<ServicoResidencia>();
			servicos=daors.findServicoEmAbertoByPrestador(prestador);
		
//				se nao existir nenhum servico aberto
				if(servicos.isEmpty()){
					//entrada
					System.out.println("nao existe servico em aberto");
						
						this.entrou= true;
						this.saiu= false;
						//criar um novo servico
						servico= new ServicoResidencia();
						//registrando entrada
						registrarEntrada();
						
					
				}else{
					System.out.println("Servico em aberto");
					//procurando por servico iniciado de hj
					
					
					for(ServicoResidencia sr: servicos){
						Calendar dataIn = Calendar.getInstance();
						dataIn.setTime(sr.getDataEntrada());
						if(dataIn.DATE == dataHj.DATE ){
							System.out.println("Servico: " + sr.toString());
							servico= sr;
							this.entrou = false;
							this.saiu= true;
							registrarSaida();
						}
						
					}
					
					
				}
		}
		public void registrarMovimentacao(){
		}
		public void registrarSaida(){
			
			servico.setDataSaida(new Date(System.currentTimeMillis()));
				DateTime horarioSaidaServico= new DateTime(servico.getHoraSaida());
			servico.setHoraSaida(new Date(System.currentTimeMillis()));
				DateTime horarioSaidaPrestador= new DateTime(prestador.getHoraSaida());
			servico.setConcluido(true);
			System.out.println("horario de saida permitida para o prestador: "+horarioSaidaPrestador.getMinuteOfDay());
			System.out.println("horario de saida atual : "+ horarioSaidaServico.getMinuteOfDay());
			
				if(horarioSaidaServico.getMinuteOfDay() > horarioSaidaPrestador.getMinuteOfDay()-15 &&  horarioSaidaServico.getMinuteOfDay() < horarioSaidaPrestador.getMillisOfDay()+15){
					dao.open();
					dao.begin();
					saiu= true;
//					this.iniciarServico= false;
					resetSenha();
					senhaEntrada= null;
					displayInfoMessageToUser("Horario de saida: "+ servico.getDataSaida().toString() + servico.getHoraSaida().toString());
					System.out.println("Horario de saida: "+ servico.getDataSaida().toString() + servico.getHoraSaida().toString());
					daors.merge(servico);
					dao.commit();
				}else{
					displayErrorMessageToUser("Horário de saída diferente do horário permitido para o prestador!");
				}
			
			
		}
		public void registrarEntrada(){
			
			servico.setDataEntrada(new Date(System.currentTimeMillis()));
				DateTime horarioEntradaServico= new DateTime(servico.getHoraEntrada());
			servico.setHoraEntrada(new Date(System.currentTimeMillis()));
				DateTime horarioEntradaPrestador= new DateTime(prestador.getHoraEntrada());
			
			System.out.println("horario de entrada permitida para o prestador: "+horarioEntradaPrestador.getMinuteOfDay());
			System.out.println("horario de entrada atual : "+ horarioEntradaServico.getMinuteOfDay());
			
			DateTime diasSemUso = new DateTime(prestador.getUltimoAcesso());
			
//				if(diasSemUso.)
			
						if(horarioEntradaPrestador.getMinuteOfDay() > horarioEntradaServico.getMinuteOfDay()-15 && horarioEntradaPrestador.getMinuteOfDay() < horarioEntradaServico.getMinuteOfDay()+15){
								System.out.println("hora de entrada prevista");
							dao.open();
							dao.begin();
								
//							this.entrou=false;
//							this.saiu= false;
							
							servico.setConcluido(false);
//							this.iniciarServico= false;
							servico.setPrestador(prestador);
							prestador.addServico(servico);
							daors.persist(servico);
							dao.commit();
							
								displayInfoMessageToUser("Horario de entrada: "+ servico.getDataEntrada().toString() + servico.getHoraEntrada().toString());
								System.out.println("Horario de entrada: "+ servico.getDataEntrada().toString() + servico.getHoraEntrada().toString());
								
								resetSenha();
							
						}else{
							displayErrorMessageToUser("Horário de entrada diferente do horário permitido para o prestador!");
						}
			
			
		}
		
		
		
		

		public List<ServicoResidencia> getServicosEmAberto() {
			dao.open();
			dao.begin();
			servicosEmAberto= daors.findServicoEmAberto();
			return servicosEmAberto;
		}

		public void setServicosEmAberto(List<ServicoResidencia> servicosEmAberto) {
			this.servicosEmAberto = servicosEmAberto;
		}

		public String getSenhaEntrada() {
			return senhaEntrada;
		}

		public void setSenhaEntrada(String senhaEntrada) {
			this.senhaEntrada = senhaEntrada;
		}

		public boolean isSaiu() {
			return saiu;
		}

		public void setSaiu(boolean saiu) {
			this.saiu = saiu;
		}

		public String getSenhaSaida() {
			return senhaEntrada;
		}

		public void setSenhaSaida(String senhaEntrada) {
			this.senhaEntrada = senhaEntrada;
		}

		public boolean isEntrou() {
			return entrou;
		}
		
		public Condominio getCondominio() {
			DAOCondominio daoc= new DAOCondominio();
			dao.open();
			dao.begin();
			condominio= daoc.find(1);
			dao.close();
			return condominio;
		}
		public void setCondominio(Condominio condominio) {
			this.condominio = condominio;
		}
		public void setEntrou(boolean entrou) {
			this.entrou = entrou;
		}

		public boolean isIniciarServico() {
			return iniciarServico;
		}

		public void setIniciarServico(boolean iniciarServico) {
			this.iniciarServico = iniciarServico;
		}

		public ServicoResidencia getServico() {
			return servico;
		}

		public void setServico(ServicoResidencia servico) {
			this.servico = servico;
		}

		public boolean issaiu() {
			return saiu;
		}

		public void setsaiu(boolean saiu) {
			this.saiu = saiu;
		}

		public Prestador getPrestador() {
			return prestador;
		}

		public void setPrestador(Pessoa prestador) {
			this.prestador = prestador;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		
		public String redirectMovimentacaoPrestador(){
			
			return "movimentacao-prestador?faces-redirect=true";
			
		}
		public void resetSenha(){
			this.senha= null;
		}
		public void resetServico(){
			this.servico = new ServicoResidencia();
		}
	
		
		

}
