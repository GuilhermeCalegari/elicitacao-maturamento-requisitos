package estoque.integration.dao;

import java.util.Collection;


// Generic Data Access Object 
public interface DAO {
	// Padrão Factory Method : FactoryMethod
	public abstract void salvar(Object object)throws Exception;
	public abstract Object consultar(Class classe, Integer id) throws Exception;
	public abstract Object consultar(Class classe, String campo, String valor) throws Exception;
	public abstract Object consultarUltimo(Class classe, String campo) throws Exception;
	public abstract void apagar(Object object) throws Exception;
	public abstract void alterar(Object object) throws Exception;
	public abstract Collection listar(Class tabela) throws Exception;
	public abstract Collection listar(Class tabela, String order) throws Exception;
	public abstract Collection pesquisar(Class classe, String campo, String valor, String order) throws Exception;

}
