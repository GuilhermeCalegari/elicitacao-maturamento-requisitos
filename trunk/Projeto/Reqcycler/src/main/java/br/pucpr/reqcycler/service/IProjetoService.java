package br.pucpr.reqcycler.service;

import java.util.List;

import br.pucpr.reqcycler.model.Projeto;

/**
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
public interface IProjetoService {
	
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
