package mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAO;
import dao.DAOPrestadorPessoa;
import model.Pessoa;
import model.ServicoResidencia;

@ManagedBean
@ViewScoped
public class ServicoPrestadorMB extends AbstractMB implements Serializable {
		private List<Pessoa> prestadores;
		private List<ServicoResidencia> servicosEmAberto;
		private DAOPrestadorPessoa daop= new DAOPrestadorPessoa();
		private DAO dao = new DAO();
		private String senha;
		private Pessoa prestador;
		private ServicoResidencia servico;
		
		private String senhaEntrada;
		
		
		private boolean entrou;
		private boolean saiu;
		private boolean iniciarServico;
		
		
		public ServicoPrestadorMB() {
			this.prestadores= new ArrayList<Pessoa>();
			this.saiu= false;
			this.iniciarServico= false;
			this.servico = new ServicoResidencia();
			this.prestador= new Pessoa();
		}

		public List<Pessoa> getPrestadores() {
			dao.open();
			dao.begin();
			prestadores=daop.findAll();
			dao.close();
			return prestadores;
			
			
		}
		
		
		
		

		public List<ServicoResidencia> getServicosEmAberto() {
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

		public Pessoa getPrestador() {
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

		public void setPrestadores(List<Pessoa> prestadores) {
			
			this.prestadores = prestadores;
		}
		public String redirectMovimentacaoPrestador(){
			
			return "movimentacao-prestador?faces-redirect=true";
			
		}
		public void resetSenha(){
			this.senha= null;
		}
		public void keyPressSenha(){
			System.out.println("digitou");
			this.iniciarServico= true;
//			displayInfoMessageToUser("senha digitada: " + senha.toString());
			if(!senha.equalsIgnoreCase(senhaEntrada) && !senha.equalsIgnoreCase("")){
				this.entrou= true;
				this.saiu= false;
			}else{
				this.entrou = false;
				this.saiu= true;
			}
			
			
			
		}
		public void registrarMovimentacao(){
		}
		public void registrarSaida(){
			servico.setDataSaida(new Date(System.currentTimeMillis()));
			servico.setHoraSaida(new Date(System.currentTimeMillis()));
			servico.setConcluido(true);
			saiu= true;
			this.iniciarServico= false;
			resetSenha();
			senhaEntrada= null;
			displayInfoMessageToUser("Horario de saida: "+ servico.getDataSaida().toString() + servico.getHoraSaida().toString());
			
			
		}
		public void registrarEntrada(){
			senhaEntrada = senha;
			servico.setDataEntrada(new Date(System.currentTimeMillis()));
			servico.setHoraEntrada(new Date(System.currentTimeMillis()));
			this.entrou=false;
			this.saiu= false;
			resetSenha();
			servico.setConcluido(false);
			this.iniciarServico= false;
			displayInfoMessageToUser("Horario de entrada: "+ servico.getDataEntrada().toString() + servico.getHoraEntrada().toString());
			
			
			
		}
		
		

}
