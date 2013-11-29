package br.pucpr.reqcycler.dao;

import java.util.List;

import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.model.Requisito;

/**
 * 
 * Requisito DAO Interface
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
public interface IRequisitoDAO {

	/**
	 * Adiciona Requisito
	 * 
	 * @param	Requisito requisito
	 */	
	public void adicionaRequisito(Requisito requisito);

	/**
	 * Atualiza Requisito
	 * 
	 * @param	Requisito requisito
	 */	
	public void atualizaRequisito(Requisito requisito);
	
	/**
	 * Deleta Requisito
	 * 
	 * @param	Requisito requisito
	 */	
	public void deletaRequisito(Requisito requisito);
	
	/**
	 * Get Requisito
	 * 
	 * @param  int Requisito Id
	 */
	public Requisito getRequisitoById(int id);
	
	/**
	 * Get Requisito List
	 * 
	 */
	public List<Requisito> getRequisitos();
	
	/**
	 * Get Requisito List by Projeto
	 * 
	 * @param  Projeto projeto
	 */
	public List<Requisito> getRequisitosByProjeto(Projeto projeto);
	
}
