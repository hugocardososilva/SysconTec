package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Pessoa;
import model.Prestador;
import model.ServicoResidencia;

public class DAOServicoResidencia extends DAO<ServicoResidencia> {
	DateFormat date= new SimpleDateFormat("yyyy-MM-dd");
	
	public List<ServicoResidencia> findServicoEmAbertoByPrestador(Prestador p) throws NullPointerException{
		try {
//		Query q = manager.createQuery("Select S from ServicoResidencia S, IN(S.prestador) P  where  P.id = :id and S.concluido = FALSE");
			Query q = manager.createQuery("Select S from ServicoResidencia S where S.concluido = FALSE and S.prestador.id = :id"  );
		String prepared= "%";
		prepared+= p.getId();
		prepared+= "%";
		
		q.setParameter("id", p.getId());
		
		
		return q.getResultList();
		} catch (NoResultException e) {
			System.out.println(e.toString());
			return null;
		}
		
	}
	public List<ServicoResidencia> findServicoEmAberto(){
		try {
	
			Query q = manager.createQuery("Select S from ServicoResidencia S where S.concluido = FALSE "  );
		
		
		
		return q.getResultList();
		} catch (NoResultException e) {
			System.out.println(e.toString());
			return null;
		}
		
	}
	public List<ServicoResidencia> findServicoByPrestadorData(Pessoa p, Date data) throws NullPointerException{
		try {
//		Query q = manager.createQuery("Select S from ServicoResidencia S, IN(S.prestador) P  where  P.id = :id and S.concluido = FALSE");
			Query q = manager.createQuery("Select S from ServicoResidencia S where S.prestador.id = :id and S.dataEntrada = :data"  );
		String prepared= "%";
		prepared+= p.getId();
		prepared+= "%";
		
		q.setParameter("id", p.getId());
		q.setParameter("data", data);
		
		System.out.println(q.getResultList().toString());
		return q.getResultList();
		} catch (NoResultException e) {
			System.out.println(e.toString());
			return null;
		}
		
	}
	

}
