package br.pucpr.reqcycler.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.pucpr.reqcycler.enumeration.StatusProjetoEnum;
import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.model.Usuario;
import br.pucpr.reqcycler.service.impl.ProjetoService;
import br.pucpr.reqcycler.service.impl.UsuarioService;

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
	
	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;
	
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@ManagedProperty(value= "#{usuarioController.usuarioLogado}")
	private Usuario usuarioLogado;		
	
	/**
	 * @param usuarioLogado the usuarioLogado to set
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	private Projeto projeto; 		
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	
	@PostConstruct
	private void init(){
		this.projeto = new Projeto();
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
	
	public void limparProjeto() {		
		this.projeto = new Projeto();
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
		
}