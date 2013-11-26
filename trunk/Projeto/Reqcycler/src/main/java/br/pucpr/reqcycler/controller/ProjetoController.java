package br.pucpr.reqcycler.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.pucpr.reqcycler.enumeration.StatusProjetoEnum;
import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.model.Usuario;
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
public class ProjetoController implements Serializable {
			
	private static final long serialVersionUID = 1674553816474363886L;

	@ManagedProperty(value = "#{projetoService}")
	private ProjetoService projetoService;
	
	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}
	
	@ManagedProperty(value= "#{usuarioController.usuarioLogado}")
	private Usuario usuarioLogado;		
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	private Projeto projeto; 	
	
	private List<Projeto> projetoList;
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	
	@PostConstruct
	private void init(){
		limparProjeto();
	}
		
	public void adicionaProjeto() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			
			projeto.setSponsor(this.usuarioLogado);
			projeto.setDataInicio(new Date());								
			projeto.setStatus(StatusProjetoEnum.ABERTO);			
			projetoService.adicionaProjeto(projeto);
						
			context.addMessage(null, new FacesMessage("Transação OK!", 
					                                  "PROJETO CRIADO COM SUCESSO!"));
			
			limparProjeto();
			
		} catch (Exception e) {	
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transação NÃO OK!", 
					                                  "PROJETO NÃO FOI CRIADO!"));
		} 					

	}
	
	public void atualizarProjeto(RowEditEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try{
					
			Projeto projeto = (Projeto) event.getObject();
			projetoService.atualizaProjeto(projeto);
			
			context.addMessage(null, new FacesMessage("Transação OK!", 
                    								  "PROJETO ALTERADO CRIADO COM SUCESSO!"));
			
			limparProjeto();
			
		}catch(Exception e){
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transação NÃO OK!", 
													  "PROJETO NÃO FOI ALTERADO!"));
		}
				
	}
	
	public List<Projeto> getProjetos(){
		return projetoService.getProjetos();		
	}
	
	public void limparProjeto() {		
		this.projeto = new Projeto();		
		this.projetoList = new ArrayList<Projeto>();
		this.projetoList.addAll(getProjetos());
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
	 * @return the projetoList
	 */
	public List<Projeto> getProjetoList() {
		return projetoList;
	}

	/**
	 * @param projetoList the projetoList to set
	 */
	public void setProjetoList(List<Projeto> projetoList) {
		this.projetoList = projetoList;
	}
		
}