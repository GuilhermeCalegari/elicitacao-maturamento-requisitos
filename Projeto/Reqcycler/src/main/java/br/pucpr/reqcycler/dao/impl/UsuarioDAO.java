package br.pucpr.reqcycler.dao.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.pucpr.reqcycler.dao.IUsuarioDAO;
import br.pucpr.reqcycler.model.Usuario;
import br.pucpr.reqcycler.util.EntityManagerControl;

/**
 * 
 * User DAO
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="usuarioDAO")
@SessionScoped
public class UsuarioDAO implements IUsuarioDAO {		

	
	@Override
	public void adicionaUsuario(Usuario usuario) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();		
	}
	
	@Override	
	public void atualizaUsuario(Usuario usuario) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		entityManager.getTransaction().begin();
        entityManager.merge(usuario);
        entityManager.getTransaction().commit();
	}
	
	@Override
	public void deletaUsuario(Usuario usuario) {
		  EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		  entityManager.getTransaction().begin();
		  usuario = entityManager.find(Usuario.class, usuario.getId());
          entityManager.remove(usuario);
          entityManager.getTransaction().commit();
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Usuario getUsuarioById(int id) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		List list = entityManager.createQuery("FROM USUARIO WHERE id = " + id).getResultList();
		return (Usuario)list.get(0);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Usuario> getUsuarios() {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();		
		List list = entityManager.createQuery("FROM " + Usuario.class.getName())
                .getResultList();
		return list;
	}

}
