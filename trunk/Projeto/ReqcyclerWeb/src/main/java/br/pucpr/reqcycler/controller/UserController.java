package br.pucpr.reqcycler.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.hibernate.exception.DataException;

import br.pucpr.reqcycler.model.User;
import br.pucpr.reqcycler.service.IUserService;

/**
 * 
 * User Managed Bean
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="UserController")
@SessionScoped
public class UserController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(UserController.class);
	
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	
	IUserService userService;
		
	List<User> userList;
	
	private int id;
	private String name;	
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
		
	public String addUser() {
		try {
			User user = new User();
			user.setId(getId());
			user.setName(getName());			
			getUserService().addUser(user);
			return SUCCESS;
		} catch (DataException e) {
			e.printStackTrace();
		} 	
		
		return ERROR;
	}
	
	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setId(0);
		this.setName("");
	}
	
	/**
	 * Get User List
	 * 
	 * @return List - User List
	 */
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}
	
	/**
	 * Get User Service
	 * 
	 * @return IUserService - User Service
	 */
	public IUserService getUserService() {		
		return this.userService;
	}

	/**
	 * Set User Service
	 * 
	 * @param IUserService - User Service
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
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
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set User Id
	 * 
	 * @param int - User Id
	 */
	public void setId(int id) {
		this.id = id;
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
	
}