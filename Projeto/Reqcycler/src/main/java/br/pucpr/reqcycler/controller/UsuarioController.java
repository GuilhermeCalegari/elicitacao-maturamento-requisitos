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
import javax.faces.model.SelectItem;

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
public class UsuarioController implements Serializable{
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;
	
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
		
	private Usuario usuario; 
		
	private Usuario usuarioLogado;
	
	private Usuario usuarioSelecionado;
	
	private List<Usuario> usuarioList;
	
	private List<Usuario> usuarioFiltroList;
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	
	@PostConstruct
	private void init(){
		limparUsuario();
	}
	
	public void limparUsuario() {		
		this.usuario = new Usuario();
		this.usuarioList = new ArrayList<Usuario>();
		this.usuarioList.addAll(getUsuarios());
	}	
	
	public List<Usuario> getUsuarios(){
		return usuarioService.getUsuarios();		
	}

	public SelectItem[] getUsuariosValues() {
		List<Usuario> listUsuarios = this.getUsuarios();
	    SelectItem[] items = new SelectItem[listUsuarios.size()];
	    int i = 0;
	    for(Usuario usuario: listUsuarios) {
	      items[i++] = new SelectItem(usuario.getId(), 
	    		  					  usuario.getNome()
	    		  					  +" "+ 
	    		  					  usuario.getSobrenome());
	    }
	    return items;
	}
		
	public void adicionaUsuario() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			
			usuario.setDataInicio(new Date());						
			usuarioService.adicionaUsuario(usuario);
						
			context.addMessage(null, new FacesMessage("Transa��o OK!", 
					                                  "USUARIO CRIADO COM SUCESSO!"));
			
			limparUsuario();
			
		} catch (Exception e) {										
			context.addMessage(null, new FacesMessage("Transa��o N�O OK!", 
					                                  "USUARIO N�O FOI CRIADO!"));
		} 					

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

	/**
	 * @return the usuarioSelecionado
	 */
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	/**
	 * @param usuarioSelecionado the usuarioSelecionado to set
	 */
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	/**
	 * @return the usuarioList
	 */
	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	/**
	 * @param usuarioList the usuarioList to set
	 */
	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	/**
	 * @return the usuarioFiltroList
	 */
	public List<Usuario> getUsuarioFiltroList() {
		return usuarioFiltroList;
	}

	/**
	 * @param usuarioFiltroList the usuarioFiltroList to set
	 */
	public void setUsuarioFiltroList(List<Usuario> usuarioFiltroList) {
		this.usuarioFiltroList = usuarioFiltroList;
	}
		
		
}