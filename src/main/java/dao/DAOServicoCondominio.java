package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Prestador;
import model.ServicoCondominio;
import model.ServicoResidencia;

public class DAOServicoCondominio extends DAO<ServicoCondominio> {

	public List<ServicoCondominio> findServicoEmAbertoByPrestador(Prestador p){
		try {

			Query q = manager.createQuery("Select S from ServicoCondominio S where S.concluido = FALSE and S.prestador.id = :id"  );
		
		q.setParameter("id", p.getId());
		
		
		return q.getResultList();
		} catch (NoResultException e) {
			System.out.println(e.toString());
			return null;
		}
		
	}
}
