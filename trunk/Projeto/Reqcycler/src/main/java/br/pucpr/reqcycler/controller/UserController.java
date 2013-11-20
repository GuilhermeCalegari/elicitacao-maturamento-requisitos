package br.pucpr.reqcycler.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.pucpr.reqcycler.model.User;
import br.pucpr.reqcycler.service.impl.UserService;

/**
 * 
 * User Managed Bean
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
		
	private UserService userService;
	    
	List<User> userList;
		
	private String name;
	private String login;
	private String password;
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
		
	public String addUser() {
		try {
			User user = new User();
			user.setLogin(getLogin());
			user.setPassword(getPassword());
			user.setName(getName());
			
			userService = new UserService(); 
			userService.addUser(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
		return ERROR;
	}
	
	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setLogin("");
		this.setPassword("");
		this.setName("");
	}
	
	/**
	 * Get User List
	 * 
	 * @return List - User List
	 */
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(userService.getUsers());
		return userList;
	}
		
	/**
	 * Set User List
	 * 
	 * @param List - User List
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
		
	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set User Name
	 * 
	 * @param String - User Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}