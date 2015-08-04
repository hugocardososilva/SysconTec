package scheduler;

import java.util.ArrayList;
import java.util.List;

import model.Condominio;
import model.Pessoa;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.DAO;
import dao.DAOCondominio;
import dao.DAOPrestadorPessoa;
import util.VerificarPrestador;

public class Tarefa implements Job{
	private DAO dao= new DAO();
	private DAOPrestadorPessoa daop= new DAOPrestadorPessoa();
	private DAOCondominio daoc= new DAOCondominio();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	
	
		System.out.println("tarefa:");
		dao.open();
		dao.begin();
	
		
		Condominio c = daoc.find(1L);
		
		System.out.println("verificando condominio .... " + c.toString());
		List<Pessoa> pessoas= new ArrayList<Pessoa>();
		pessoas= daop.findAll();
		DateTime hoje = new DateTime(System.currentTimeMillis());
		for(Pessoa p : pessoas){
			System.out.println("verificando pessoa : " + p.getSenha());
			DateTime ultimo= new DateTime(p.getUltimoAcesso());
			int dias = Days.daysBetween(ultimo, hoje).getDays();
			System.out.println("dias : "+ dias);
			
			if(dias < c.getDiasSemUtilizarSistema()){
				p.setBloqueado(true);
				daop.merge(p);
			}
			
		}
		dao.commit();
		
	}

}
