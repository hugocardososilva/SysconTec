package util;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import model.Condominio;
import model.Pessoa;
import dao.DAOCondominio;
import dao.DAOPrestadorPessoa;

public class VerificarPrestador {
	private DAOPrestadorPessoa dao= new DAOPrestadorPessoa();
	private DAOCondominio daoc= new DAOCondominio();
	
	
	public VerificarPrestador() {
		// TODO Auto-generated constructor stub
	}
	public void verificar(){
		dao.open();
		dao.begin();
		
		Condominio c = daoc.find(1);
		System.out.println("verificando condominio .... " + c.toString());
		List<Pessoa> pessoas= new ArrayList<Pessoa>();
		pessoas= dao.findAll();
		DateTime hoje = new DateTime(System.currentTimeMillis());
		for(Pessoa p : pessoas){
			System.out.println("verificando pessoa : " + p.getSenha());
			DateTime ultimo= new DateTime(p.getUltimoAcesso());
			int dias = Days.daysBetween(ultimo, hoje).getDays();
			
			if(dias < c.getDiasSemUtilizarSistema()){
				p.setBloqueado(true);
				dao.merge(p);
			}
			
		}
		dao.commit();
		
	}

}
