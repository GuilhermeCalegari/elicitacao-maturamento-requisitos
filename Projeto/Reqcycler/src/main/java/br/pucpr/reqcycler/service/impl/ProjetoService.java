package br.pucpr.reqcycler.service.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.pucpr.reqcycler.dao.impl.ProjetoDAO;
import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.service.IProjetoService;

/**
 * 
 * Projeto Service
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
@ManagedBean(name="projetoService")
@SessionScoped
public class ProjetoService implements IProjetoService {
				
	@ManagedProperty(value = "#{projetoDAO}")
	private ProjetoDAO projetoDAO;
	
	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
	}

	@Override
	public void adicionaProjeto(Projeto projeto) {
		projetoDAO.adicionaProjeto(projeto);
	}

	@Override
	public void atualizaProjeto(Projeto projeto) {
		projetoDAO.atualizaProjeto(projeto);		
	}

	@Override
	public void deletaProjeto(Projeto projeto) {
		projetoDAO.deletaProjeto(projeto);		
	}

	@Override
	public Projeto getProjetoById(int id) {		
		return projetoDAO.getProjetoById(id);
	}

	@Override
	public List<Projeto> getProjetos() { 
		return projetoDAO.getProjetos();
	}


}
