package br.pucpr.reqcycler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * User Entity
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
@Entity
@Table(name="USER")
public class User {

	private int id;
	private String name;
	private String login;
	private String password;
	
	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	@Id
	@Column(name="ID", unique = true, nullable = false)
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
	@Column(name="NAME", unique = true, nullable = false)
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
	 * Get User Login
	 *  
	 * @return the login
	 */
	@Column(name="LOGIN", unique = true, nullable = false)
	public String getLogin() {
		return login;
	}

	/**
	 * Set User Login
	 * 
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Get User Password
	 * 
	 * @return the password
	 */
	@Column(name="PASSWORD", unique = true, nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * Set User Password
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id : ").append(getId());
		strBuff.append(", name : ").append(getName());		
		return strBuff.toString();
	}
}
