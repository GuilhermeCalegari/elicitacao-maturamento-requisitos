package br.pucpr.reqcycler.service.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.pucpr.reqcycler.dao.impl.RequisitoDAO;
import br.pucpr.reqcycler.model.Requisito;
import br.pucpr.reqcycler.service.IRequisitoService;

/**
 * 
 * Requisito Service
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
@ManagedBean(name="requisitoService")
@SessionScoped
public class RequisitoService implements IRequisitoService {
				
	@ManagedProperty(value = "#{requisitoDAO}")
	private RequisitoDAO requisitoDAO;
	
	public void setRequisitoDAO(RequisitoDAO requisitoDAO) {
		this.requisitoDAO = requisitoDAO;
	}

	@Override
	public void adicionaRequisito(Requisito requisito) {
		requisitoDAO.adicionaRequisito(requisito);
	}

	@Override
	public void atualizaRequisito(Requisito requisito) {
		requisitoDAO.atualizaRequisito(requisito);		
	}

	@Override
	public void deletaRequisito(Requisito requisito) {
		requisitoDAO.deletaRequisito(requisito);
		
	}

	@Override
	public Requisito getRequisitoById(int id) {		
		return requisitoDAO.getRequisitoById(id);
	}

	@Override
	public List<Requisito> getRequisitos() { 
		return requisitoDAO.getRequisitos();
	}


}
