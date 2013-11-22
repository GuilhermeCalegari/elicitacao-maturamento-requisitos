package br.pucpr.reqcycler.dao;

import java.util.List;

import br.pucpr.reqcycler.model.Projeto;

/**
 * 
 * Projeto DAO Interface
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
public interface IProjetoDAO {

	/**
	 * Adiciona Projeto
	 * 
	 * @param	Projeto projeto
	 */	
	public void adicionaProjeto(Projeto projeto);

	/**
	 * Atualiza Projeto
	 * 
	 * @param	Projeto projeto
	 */	
	public void atualizaProjeto(Projeto projeto);
	
	/**
	 * Deleta Projeto
	 * 
	 * @param	Projeto projeto
	 */	
	public void deletaProjeto(Projeto projeto);
	
	/**
	 * Get Projeto
	 * 
	 * @param  int Projeto Id
	 */
	public Projeto getProjetoById(int id);
	
	/**
	 * Get Projeto List
	 * 
	 */
	public List<Projeto> getProjetos();
}
