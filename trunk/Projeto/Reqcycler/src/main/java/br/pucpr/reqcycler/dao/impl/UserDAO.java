package br.pucpr.reqcycler.dao.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.pucpr.reqcycler.dao.IUserDAO;
import br.pucpr.reqcycler.model.User;
import br.pucpr.reqcycler.util.EntityManagerControl;

/**
 * 
 * User DAO
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="userDAO")
@SessionScoped
public class UserDAO implements IUserDAO {		

	/**
	 * Add User
	 * 
	 * @param  User user
	 */	
	public void addUser(User user) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();		
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	public void deleteUser(User user) {
		  EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		  entityManager.getTransaction().begin();
          user = entityManager.find(User.class, user.getId());
          entityManager.remove(user);
          entityManager.getTransaction().commit();
	}

	/**
	 * Update User
	 * 
	 * @param  User user
	 */	
	public void updateUser(User user) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
	}

	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */	
	public User getUserById(int id) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		List list = entityManager.createQuery("FROM User WHERE id = " + id).getResultList();
		return (User)list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */	
	public List<User> getUsers() {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		List list = entityManager.createQuery("FROM " + User.class.getName())
                .getResultList();
		return list;
	}

}
