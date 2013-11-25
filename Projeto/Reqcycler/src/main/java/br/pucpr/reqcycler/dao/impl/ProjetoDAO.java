package br.pucpr.reqcycler.dao.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.pucpr.reqcycler.dao.IProjetoDAO;
import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.model.Usuario;
import br.pucpr.reqcycler.util.EntityManagerControl;

/**
 * 
 * Projeto DAO
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="projetoDAO")
@SessionScoped
public class ProjetoDAO implements IProjetoDAO {		

	
	@Override
	public void adicionaProjeto(Projeto projeto) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();

		//Detached to Transient
		projeto.setSponsor(entityManager.
				find(Usuario.class, projeto.getSponsor().getId()));
								
		entityManager.getTransaction().begin();
		entityManager.persist(projeto);
		entityManager.getTransaction().commit();		
	}
	
	@Override	
	public void atualizaProjeto(Projeto projeto) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		entityManager.getTransaction().begin();
        entityManager.merge(projeto);
        entityManager.getTransaction().commit();
	}
	
	@Override
	public void deletaProjeto(Projeto projeto) {
		  EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		  entityManager.getTransaction().begin();
		  projeto = entityManager.find(Projeto.class, projeto.getId());
          entityManager.remove(projeto);
          entityManager.getTransaction().commit();
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Projeto getProjetoById(int id) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		List list = entityManager.createQuery("FROM USUARIO WHERE id = " + id).getResultList();
		return (Projeto)list.get(0);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Projeto> getProjetos() {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();		
		List list = entityManager.createQuery("FROM " + Projeto.class.getName())
                .getResultList();
		return list;
	}

}
