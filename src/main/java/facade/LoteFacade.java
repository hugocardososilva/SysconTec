package facade;

import model.Lote;
import dao.DAO;
import dao.DAOLote;

public class LoteFacade {
	private DAOLote daol= new DAOLote();
	private DAO dao= new DAO();
	
	public LoteFacade() {
		// TODO Auto-generated constructor stub
	}
	
	public Lote pesquisarLote(Long id){
		dao.open();
		dao.begin();
		Lote lote= new Lote();
		lote=daol.find(id);
//		dao.close();
		return lote;
		
	}

	public DAOLote getDaol() {
		return daol;
	}

	public void setDaol(DAOLote daol) {
		this.daol = daol;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}
	

}
