package br.pucpr.reqcycler.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.pucpr.reqcycler.model.Usuario;
import br.pucpr.reqcycler.service.impl.UsuarioService;

/**
 * 
 * Usuario Managed Bean
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="usuarioController")
@SessionScoped
public class UsuarioController {
			
	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;
	
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
		
	private Usuario usuario; 
		
	private Usuario usuarioLogado;
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	
	@PostConstruct
	private void init(){
		this.usuarioLogado = null;
		this.usuario = new Usuario();
	}
		
	public void adicionaUsuario() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			
			usuario.setDataInicio(new Date());						
			usuarioService.adicionaUsuario(usuario);
						
			context.addMessage(null, new FacesMessage("Transação OK!", 
					                                  "USUARIO CRIADO COM SUCESSO!"));
			
			limparUsuario();
			
		} catch (Exception e) {										
			context.addMessage(null, new FacesMessage("Transação NÃO OK!", 
					                                  "USUARIO NÃO FOI CRIADO!"));
		} 					

	}
	
	public void limparUsuario() {		
		this.usuario = new Usuario();
	}	
			
	public void logon(){
		try{				
			if(this.usuario.getLogin().equalsIgnoreCase("admin")){
				this.usuarioLogado = this.usuario;
			}else{
				this.usuarioLogado = usuarioService.getUsuarioByLogin(this.usuario.getLogin());
			}			
			limparUsuario();			
		}catch(Exception e){
			
		}
		
	}
	
	public String logout(){
		this.usuarioLogado = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "/index.xhtml?faces-redirect=true";
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the usuarioLogado
	 */
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado the usuarioLogado to set
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
		
}