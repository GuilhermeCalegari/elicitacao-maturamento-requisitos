package br.pucpr.reqcycler.service.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.pucpr.reqcycler.dao.impl.UsuarioDAO;
import br.pucpr.reqcycler.model.Usuario;
import br.pucpr.reqcycler.service.IUsuarioService;

/**
 * 
 * User Service
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
@ManagedBean(name="usuarioService")
@SessionScoped
public class UsuarioService implements IUsuarioService {
				
	@ManagedProperty(value = "#{usuarioDAO}")
	private UsuarioDAO usuarioDAO;
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	public void adicionaUsuario(Usuario usuario) {
		usuarioDAO.adicionaUsuario(usuario);
	}

	@Override
	public void atualizaUsuario(Usuario usuario) {
		usuarioDAO.atualizaUsuario(usuario);		
	}

	@Override
	public void deletaUsuario(Usuario usuario) {
		usuarioDAO.deletaUsuario(usuario);
		
	}

	@Override
	public Usuario getUsuarioById(int id) {		
		return usuarioDAO.getUsuarioById(id);
	}

	@Override
	public List<Usuario> getUsuarios() { 
		return usuarioDAO.getUsuarios();
	}


}
