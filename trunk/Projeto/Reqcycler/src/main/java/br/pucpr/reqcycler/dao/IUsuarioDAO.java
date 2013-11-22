package br.pucpr.reqcycler.dao;

import java.util.List;

import br.pucpr.reqcycler.model.Usuario;

/**
 * 
 * Usuario DAO Interface
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
public interface IUsuarioDAO {

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
	 * Get Usuario List
	 * 
	 */
	public List<Usuario> getUsuarios();
}
