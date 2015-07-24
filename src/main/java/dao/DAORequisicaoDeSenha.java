package dao;

import java.util.List;

import javax.persistence.Query;

import model.RequisicaoDeSenha;
import model.Usuario;

public class DAORequisicaoDeSenha extends DAO<RequisicaoDeSenha> {
	public RequisicaoDeSenha findByHash(String hash){
		
		Query q= manager.createQuery("SELECT R from RequisicaoDeSenha R where R.hash like :hash");
		q.setParameter("hash", hash);
		
		return (RequisicaoDeSenha) q.getSingleResult();
		
	}
public List<RequisicaoDeSenha> findByEmail(String email){
		
		Query q= manager.createQuery("SELECT R from RequisicaoDeSenha R where R.email like :email and R.valido = true");
		q.setParameter("email", email);
		
		return (List<RequisicaoDeSenha>) q.getResultList();
		
	}

}
