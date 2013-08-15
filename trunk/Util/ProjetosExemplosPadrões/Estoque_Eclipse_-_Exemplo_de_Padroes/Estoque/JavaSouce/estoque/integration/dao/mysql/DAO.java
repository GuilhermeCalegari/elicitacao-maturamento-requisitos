package estoque.integration.dao.mysql;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import estoque.util.HibernateUtil;



// Generic Data Access Object 
public class DAO implements estoque.integration.dao.DAO {

	public DAO() {
	}
	
	public void salvar(Object object)throws Exception{
		try{
			Session hibernate = HibernateUtil.getSessao();
			hibernate.save(object);
			HibernateUtil.confirma();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
	}

	// get
	// O get é melhor que o load porque procura no Cache primeiro
	public Object consultar(Class classe, Integer id) throws Exception{
		Object object = null;
		try{
			Session hibernate = HibernateUtil.getSessao();
			object = hibernate.get(classe,id);
			HibernateUtil.fechaSessao();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
		return object;
	}	

	public Object consultar(Class classe, String campo, String valor) throws Exception{
		Object object = null;
		try{
			Session hibernate = HibernateUtil.getSessao();
			Criteria criteria = hibernate.createCriteria(classe);
			// Faz o critério de pesquisa
			criteria.add(Expression.eq(campo, valor));
			object = criteria.uniqueResult();
			HibernateUtil.fechaSessao();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
		return object;
	}	
	
	public Object consultarUltimo(Class classe, String campo) throws Exception{
		Object object = null;
		try{
			Session hibernate = HibernateUtil.getSessao();
			Criteria criteria = hibernate.createCriteria(classe);
			
			// Faz a pesquisa pelo último
	        criteria.setProjection(Projections.max(campo));
			object = criteria.uniqueResult();
			HibernateUtil.fechaSessao();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
		return object;
	}	
	
	public void apagar(Object object) throws Exception{
		try{
			Session hibernate = HibernateUtil.getSessao();
			hibernate.delete(object);
			HibernateUtil.confirma();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
	}
	
	public void alterar(Object object) throws Exception{
		try{
			Session hibernate = HibernateUtil.getSessao();
			hibernate.update(object);
			HibernateUtil.confirma();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
	}	
	
	// alterado
	public Collection listar(Class tabela) throws Exception{
		Collection objects = null;
		try{
			Session hibernate = HibernateUtil.getSessao();
			Criteria criteria = hibernate.createCriteria(tabela);
			objects = criteria.list();
			HibernateUtil.fechaSessao();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
		return objects;
	}	
	
	// novos
	public Collection listar(Class tabela, String order) throws Exception{
		Collection objects = null;
		try{
			Session hibernate = HibernateUtil.getSessao();
			Criteria criteria = hibernate.createCriteria(tabela);
			criteria.addOrder(Order.desc(order));
			objects = criteria.list();
			HibernateUtil.fechaSessao();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
		return objects;
	}		
	

	public Collection pesquisar(Class classe, String campo, String valor, String order) throws Exception{
		
		System.out.println("Pesquisar " + campo + valor + order);
		Collection objects = null;
		try{
			
			// A classe critéria faz um filtro e já retorna uma coleção
			Session hibernate = HibernateUtil.getSessao();
			Criteria criteria = hibernate.createCriteria(classe);
			
			// Define a ordenação da classe
			criteria.addOrder(Order.asc(order));
			criteria.add(Expression.like(campo, valor, MatchMode.START));
			objects = criteria.list();
			HibernateUtil.fechaSessao();
		}
		catch(Exception e){
			HibernateUtil.aborta();
			throw new Exception(e);
		}
		return objects;
	}	

}
