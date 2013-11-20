package br.pucpr.reqcycler.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.pucpr.reqcycler.dao.IUserDAO;
import br.pucpr.reqcycler.model.User;

/**
 * 
 * User DAO
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

public class UserDAO implements IUserDAO {		

	protected EntityManager entityManager;
	 
    public UserDAO() {
        entityManager = getEntityManager();
    }
 
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("ReqcyclerPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
 
        return entityManager;
    }

	/**
	 * Add User
	 * 
	 * @param  User user
	 */	
	public void addUser(User user) {
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
		List list = entityManager.createQuery("FROM User WHERE id = " + id).getResultList();
		return (User)list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */	
	public List<User> getUsers() {
		List list = entityManager.createQuery("FROM " + User.class.getName())
                .getResultList();
		return list;
	}

}
