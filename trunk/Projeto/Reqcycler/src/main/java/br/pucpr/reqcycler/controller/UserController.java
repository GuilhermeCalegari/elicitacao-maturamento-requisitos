package br.pucpr.reqcycler.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.pucpr.reqcycler.model.User;
import br.pucpr.reqcycler.service.impl.UserService;

/**
 * 
 * User Managed Bean
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="userController")
@SessionScoped
public class UserController implements Serializable {
			
	private static final long serialVersionUID = 3128032094784026646L;
	
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
		
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	List<User> userList;
	
	private String name;
	
	private String login;
	
	private String password;
	
	private boolean loggedUser;
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	
	@PostConstruct
	private void init(){
		this.loggedUser = false;
	}
		
	public void addUser() {
		try {
			User user = new User();
			user.setLogin(this.login);
			user.setPassword(this.password);
			user.setName(this.name);
			
			userService.addUser(user);
			
			FacesContext context = FacesContext.getCurrentInstance();			
			context.addMessage(null, new FacesMessage("Transação OK!", 
					                                  "USUARIO CRIADO COM SUCESSO!"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext context = FacesContext.getCurrentInstance();			
			context.addMessage(null, new FacesMessage("Transação NÃO OK!", 
					                                  "USUARIO NÃO FOI CRIADO!"));
		} 			

	}
	
	/**
	 * Reset Fields 
	 */
	public void reset() {
		this.name = "";
		this.login = "";
		this.password = "";
	}	
	
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(userService.getUsers());
		return userList;
	}
	
	public void logon(){
		this.loggedUser = true;
	}
	
	public void logout(){
		this.loggedUser = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(boolean loggedUser) {
		this.loggedUser = loggedUser;
	}
		
}