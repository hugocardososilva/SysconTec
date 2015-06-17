package dao;

import javax.persistence.Query;

import model.Usuario;

public class DAOUser extends DAO<Usuario> {
	
	public Usuario getByEmail(String email){
		
		Query q= manager.createQuery("SELECT U from Usuario U where U.email like :email");
		q.setParameter("email", email);
		
		return (Usuario) q.getSingleResult();
		
	}
	public Usuario getByLogin(String login){
		
		Query q= manager.createQuery("SELECT U from Usuario U where U.login like :login");
		q.setParameter("login", login);
		
		return (Usuario) q.getSingleResult();
		
	}

}
