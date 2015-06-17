package util;

import java.util.Date;

import dao.DAO;
import dao.DAOFacade;
import dao.DAOPrestadorPessoa;
import dao.DAOTipoServico;
import dao.DAOUser;
import model.Funcionario;
import model.Pessoa;
import model.TipoServico;
import model.Usuario;

public class TestCad {
	public static void main(String[] args) {
//		Usuario admin = new Funcionario();
//		admin.setCpf("1234567890");
//		admin.setEmail("hugocardososilva@gmail.com");
//		admin.setSenha("admin");
//		admin.setLogin("admin");
//		admin.setNome("Administrador");
//		admin.setSobrenome("admin");
//		
//		
//		DAOUser daou= new DAOUser();
//		
//		daou.open();
//		daou.begin();
//		daou.persist(admin);
//		daou.commit();
		cadastrar();
		
	}
	public static void cadastrar(){
	DAO dao= new DAO();
	DAOTipoServico daot= new DAOTipoServico();
	DAOPrestadorPessoa daop= new DAOPrestadorPessoa();
	Pessoa p = new Pessoa();
	p.setCpf("123123123132l");
	p.setRg(123123123l);
	p.setEmail("aishdaiuhd");
	p.setSenha("12312312");
	
	TipoServico ts= new TipoServico();
	dao.open();
	dao.begin();
	ts= daot.find(1l);
	
	p.addTipo(ts);
	ts.addPrestador(p);
	
	
	
	
	daot.merge(ts);
	daop.persist(p);
	
	dao.commit();
	
	
	
		
		
	}
	
}
