package br.pucpr.reqcycler.service.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
@ManagedBean(name="userService")
@SessionScoped
public class UserService implements IUserService {
				
	@ManagedProperty(value = "#{userDAO}")
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * Add User
	 * 
	 * @param  User user
	 */	
	public void addUser(User user) {		
		userDAO.addUser(user);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */	
	public void deleteUser(User user) {
		userDAO.deleteUser(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */		
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */	
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	/**
	 * Get User List
	 * 
	 */	
	public List<User> getUsers() {	
		return userDAO.getUsers();
	}

	

}
