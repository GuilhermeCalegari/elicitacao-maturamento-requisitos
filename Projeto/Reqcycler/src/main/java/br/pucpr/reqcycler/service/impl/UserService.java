package br.pucpr.reqcycler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pucpr.reqcycler.dao.IUserDAO;
import br.pucpr.reqcycler.model.User;
import br.pucpr.reqcycler.service.IUserService;

/**
 * 
 * User Service
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
		
	@Autowired
	IUserDAO userDAO;
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	public void addUser(User user) {
		getUserDAO().addUser(user);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)	
	public void deleteUser(User user) {
		getUserDAO().deleteUser(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)	
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
