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
 * Usuario DAO
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
	public Usuario getUsuarioById(int id) {
		EntityManager entityManager =
				EntityManagerControl.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id) ;
		return usuario;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public Usuario getUsuarioByLogin(String login){
		
		List list = null;		
		try{
			EntityManager entityManager =
					EntityManagerControl.createEntityManager();			
			list = entityManager
						.createQuery("FROM "  + Usuario.class.getName() + " WHERE LOGIN =  :login")
						.setParameter("login", login)
						.getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
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
