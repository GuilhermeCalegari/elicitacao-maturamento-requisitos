package br.pucpr.reqcycler.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.pucpr.reqcycler.model.Requisito;
import br.pucpr.reqcycler.service.impl.RequisitoService;

/**
 * 
 * Requisito Managed Bean
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="requisitoController")
@SessionScoped
public class RequisitoController {
			
	@ManagedProperty(value = "#{requisitoService}")
	private RequisitoService requisitoService;
	
	public void setRequisitoService(RequisitoService requisitoService) {
		this.requisitoService = requisitoService;
	}
		
	private Requisito requisito; 
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	
	@PostConstruct
	private void init(){		
		this.requisito = new Requisito();
	}
		
	public void adicionaRequisito() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			
			requisito.setDataCriado(new Date());						
			requisitoService.adicionaRequisito(requisito);
						
			context.addMessage(null, new FacesMessage("Transação OK!", 
					                                  "REQUISITO CRIADO COM SUCESSO!"));
			
		} catch (Exception e) {										
			context.addMessage(null, new FacesMessage("Transação NÃO OK!", 
					                                  "REQUISITO NÃO FOI CRIADO!"));
		} 			

	}
	
	public void limparRequisito() {
		this.requisito = null;
		this.requisito = new Requisito();
	}	

	/**
	 * @return the requisito
	 */
	public Requisito getRequisito() {
		return requisito;
	}

	/**
	 * @param requisito the requisito to set
	 */
	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}
		
}