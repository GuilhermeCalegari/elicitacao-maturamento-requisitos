package estoque.business.bo;

import java.math.BigDecimal;
import java.util.Collection;

import estoque.business.to.Produto;
import estoque.integration.dao.ProdutoDAO;
import estoque.integration.dao.factory.DAOFactory;
import estoque.util.GlobalParameter;



// Padrão Business Object
public class ProdutoBO {
	private ProdutoDAO produtoDAO = null;
	private DAOFactory factory = new DAOFactory();
	
	public void salvar(Produto produto) {
		try {
			produtoDAO.salvar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(Produto produto) {
		try {
			produtoDAO.alterar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apagar(Produto produto) {
		try {
			produtoDAO.apagar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object consultarUltimo(Class<Produto> name, String string) {
		try {
			return produtoDAO.consultarUltimo(name, string);
		} catch (Exception e) {
			e.printStackTrace();
			return	null;
		}
	}

	public Collection listar(Class<Produto> name) {
		try {
			return produtoDAO.listar(name);
		} catch (Exception e) {
			e.printStackTrace();
			return	null;
		}
	}


	public Collection pesquisar(Class<Produto> name, String string, String text, String string2) {
		try {
			return produtoDAO.pesquisar(name, string, text, string2);
		} catch (Exception e) {
			e.printStackTrace();
			return	null;
		}
	}
	
	public Object consultar(Class<Produto> name, Integer id) {
		try {
			return produtoDAO.consultar(name, id);
		} catch (Exception e) {
			e.printStackTrace();
			return	null;
		}
	}
	
	public ProdutoBO() {
		produtoDAO = factory.getProdutoDAO();
	}

	public BigDecimal calcularPrecoVenda(BigDecimal pPrecoCusto) {
		System.out.println("Business Object Valor Custo: " + pPrecoCusto); //$NON-NLS-1$

		BigDecimal precoVenda;

		// Calcula o Preço de Venda de acordo com a Política de Preço de Venda (Strategy)
		System.out.println("Preco de Custo: " + pPrecoCusto + 
				"\nCalcula o preÃ§o de acordo com a política " + GlobalParameter.getInstance().getPoliticaPreco().getClass().getName());
		precoVenda = GlobalParameter.getInstance().getPoliticaPreco().algoritmoPreco(pPrecoCusto);
		return precoVenda;
	}
}
