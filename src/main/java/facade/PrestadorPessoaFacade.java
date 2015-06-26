package facade;

import model.Pessoa;
import model.Prestador;
import dao.DAO;
import dao.DAOPrestadorPessoa;

public class PrestadorPessoaFacade {
	private DAO dao = new DAO();
	private DAOPrestadorPessoa daoP= new DAOPrestadorPessoa();
	
	public PrestadorPessoaFacade() {
		
	}
	
	public void salvar(){
		
	}
	public Pessoa atualizar(Pessoa p){
		dao.open();
		dao.begin();
		daoP.merge(p);
		dao.close();
		return p;
	}

}
