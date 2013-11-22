package br.pucpr.reqcycler.dao.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.pucpr.reqcycler.dao.IRequisitoDAO;
import br.pucpr.reqcycler.model.Requisito;
import br.pucpr.reqcycler.util.EntityManagerControl;

/**
 * 
 * Requisito DAO
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="requisitoDAO")
@SessionScoped
public class RequisitoDAO implements IRequisitoDAO {		

	
	@Override
	public void adicionaRequisito(Requisito requisito) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(requisito);
		entityManager.getTransaction().commit();		
	}
	
	@Override	
	public void atualizaRequisito(Requisito requisito) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		entityManager.getTransaction().begin();
        entityManager.merge(requisito);
        entityManager.getTransaction().commit();
	}
	
	@Override
	public void deletaRequisito(Requisito requisito) {
		  EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		  entityManager.getTransaction().begin();
		  requisito = entityManager.find(Requisito.class, requisito.getId());
          entityManager.remove(requisito);
          entityManager.getTransaction().commit();
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Requisito getRequisitoById(int id) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		List list = entityManager.createQuery("FROM USUARIO WHERE id = " + id).getResultList();
		return (Requisito)list.get(0);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Requisito> getRequisitos() {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();		
		List list = entityManager.createQuery("FROM " + Requisito.class.getName())
                .getResultList();
		return list;
	}

}
