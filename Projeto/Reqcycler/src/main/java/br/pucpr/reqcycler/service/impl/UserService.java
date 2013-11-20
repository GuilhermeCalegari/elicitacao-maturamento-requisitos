package br.pucpr.reqcycler.service.impl;

import java.util.List;

import br.pucpr.reqcycler.dao.IUserDAO;
import br.pucpr.reqcycler.dao.impl.UserDAO;
import br.pucpr.reqcycler.model.User;
import br.pucpr.reqcycler.service.IUserService;

/**
 * 
 * User Service
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

public class UserService implements IUserService {
				
	private IUserDAO userDAO;
			
	public UserService() {
		super();		
	}

	/**
	 * Add User
	 * 
	 * @param  User user
	 */	
	public void addUser(User user) {
		userDAO = new UserDAO();
		userDAO.addUser(user);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */	
	public void deleteUser(User user) {
		getUserDAO().deleteUser(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */		
	public void updateUser(User user) {
		getUserDAO().updateUser(user);
	}
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */	
	public User getUserById(int id) {
		return getUserDAO().getUserById(id);
	}

	/**
	 * Get User List
	 * 
	 */	
	public List<User> getUsers() {	
		return getUserDAO().getUsers();
	}

	/**
	 * Get User DAO
	 * 
	 * @return IUserDAO - User DAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Set User DAO
	 * 
	 * @param IUserDAO - User DAO
	 */
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
