package estoque.integration.dao.factory;

import estoque.integration.dao.CategoriaDAO;
import estoque.integration.dao.ProdutoDAO;

/**
 * DAOFactory
 */
// Padrão Abstract Factory : ConcreteFactory
public class DAOFactory {
	
	private int database = 0;
	private final int MYSQL = 0;
	private final int POSTGRESQL = 1;
	
	
	public DAOFactory() {
		// recupera a conexao e seta o banco database a ser usado.
	}

	public CategoriaDAO getCategoriaDAO() {
		CategoriaDAO returnCategoriaDAO;
		if (database == MYSQL)
			returnCategoriaDAO = new estoque.integration.dao.mysql.CategoriaDAO();
		else
			returnCategoriaDAO = new estoque.integration.dao.postgresql.CategoriaDAO();
		
		return returnCategoriaDAO;
	}

	public ProdutoDAO getProdutoDAO() {
		ProdutoDAO returnProdutoDAO;
		if (database == MYSQL)
			returnProdutoDAO = new estoque.integration.dao.mysql.ProdutoDAO();
		else
			returnProdutoDAO = new estoque.integration.dao.postgresql.ProdutoDAO();
		
		return returnProdutoDAO;
	}
	
}