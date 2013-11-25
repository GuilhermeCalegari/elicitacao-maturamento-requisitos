package br.pucpr.reqcycler.service;

import java.util.List;

import br.pucpr.reqcycler.model.Usuario;

/**
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
public interface IUsuarioService {
	
	/**
	 * Adiciona Usuario
	 * 
	 * @param	Usuario usuario
	 */	
	public void adicionaUsuario(Usuario usuario);

	/**
	 * Atualiza Usuario
	 * 
	 * @param	Usuario usuario
	 */	
	public void atualizaUsuario(Usuario usuario);
	
	/**
	 * Deleta Usuario
	 * 
	 * @param	Usuario usuario
	 */	
	public void deletaUsuario(Usuario usuario);
	
	/**
	 * Get Usuario
	 * 
	 * @param  int Usuario Id
	 */
	public Usuario getUsuarioById(int id);
	
	/**
	 * Get Usuario
	 * 
	 * @param  String Usuario login
	 */
	public Usuario getUsuarioByLogin(String login);
	
	/**
	 * Get Usuario List
	 * 
	 */
	public List<Usuario> getUsuarios();
	
}
