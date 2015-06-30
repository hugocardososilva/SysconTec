package dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Pessoa;

public class DAOPrestadorPessoa extends DAO<Pessoa> {
	public Pessoa findBySenha(String senha){
		try {
			Query q = manager.createQuery("Select P from Pessoa P where P.senha like :senha");
			String prepared= "%";
			prepared+= senha;
			prepared+= "%";
			q.setParameter("senha", prepared);
			return (Pessoa) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.toString());
			return null;
		}
		
		
	}

}
