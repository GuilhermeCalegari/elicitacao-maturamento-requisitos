package br.pucpr.reqcycler.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.service.impl.ProjetoService;

/**
 * 
 * Projeto Managed Bean
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="projetoController")
@SessionScoped
public class ProjetoController {
			
	@ManagedProperty(value = "#{projetoService}")
	private ProjetoService projetoService;
	
	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}
		
	private Projeto projeto; 
		
	private Projeto projetoLogado;
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	
	@PostConstruct
	private void init(){
		this.projetoLogado = null;
		this.projeto = new Projeto();
	}
		
	public void adicionaProjeto() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			
			projeto.setDataCriado(new Date());						
			projetoService.adicionaProjeto(projeto);
						
			context.addMessage(null, new FacesMessage("Transação OK!", 
					                                  "PROJETO CRIADO COM SUCESSO!"));
			
		} catch (Exception e) {										
			context.addMessage(null, new FacesMessage("Transação NÃO OK!", 
					                                  "PROJETO NÃO FOI CRIADO!"));
		} 			

	}
	
	public void limparProjeto() {
		this.projeto = null;
		this.projeto = new Projeto();
	}	
			
	public void logon(){
		this.projetoLogado = this.projeto;
		limparProjeto();
	}
	
	public String logout(){
		this.projetoLogado = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "/index.xhtml?faces-redirect=true";
	}

	/**
	 * @return the projeto
	 */
	public Projeto getProjeto() {
		return projeto;
	}

	/**
	 * @param projeto the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	/**
	 * @return the projetoLogado
	 */
	public Projeto getProjetoLogado() {
		return projetoLogado;
	}

	/**
	 * @param projetoLogado the projetoLogado to set
	 */
	public void setProjetoLogado(Projeto projetoLogado) {
		this.projetoLogado = projetoLogado;
	}
		
}