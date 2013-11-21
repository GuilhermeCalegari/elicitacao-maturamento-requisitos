package br.pucpr.reqcycler.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * EntityManagerFactory
 * 
 * @author Rodrigo Moreschi Valoski
 * 
 */
@ManagedBean(name="calendarioBean")
@SessionScoped
public class CalendarioBean {

	private Date date;

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
