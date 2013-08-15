package estoque.business.bo;

import java.util.ArrayList;
import java.util.Collection;

import estoque.business.to.Categoria;
import estoque.business.to.Produto;
import estoque.business.to.Situacao;
import estoque.integration.dao.CategoriaDAO;
import estoque.integration.dao.ProdutoDAO;
import estoque.integration.dao.factory.DAOFactory;



// Padrão Business Object
public class CategoriaBO {

	private CategoriaDAO categoriaDAO = null;
	private ProdutoDAO produtoDAO = null;
	
	private DAOFactory factory = new DAOFactory();
	
	public void salvar(Categoria categoria) {
		try {
			categoriaDAO.salvar(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(Categoria categoria) {
		try {
			categoriaDAO.alterar(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apagar(Categoria categoria) {
		try {
			categoriaDAO.apagar(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object consultarUltimo(Class<Categoria> name, String string) {
		try {
			return categoriaDAO.consultarUltimo(name, string);
		} catch (Exception e) {
			e.printStackTrace();
			return	null;
		}
	}

	public Collection listar(Class<Categoria> name) {
		try {
			return categoriaDAO.listar(name);
		} catch (Exception e) {
			e.printStackTrace();
			return	null;
		}
	}


	public Collection pesquisar(Class<Categoria> name, String string, String text, String string2) {
		try {
			return categoriaDAO.pesquisar(name, string, text, string2);
		} catch (Exception e) {
			e.printStackTrace();
			return	null;
		}
	}
	
	public Object consultar(Class<Categoria> name, Integer id) {
		try {
			return categoriaDAO.consultar(name, id);
		} catch (Exception e) {
			e.printStackTrace();
			return	null;
		}
	}
	
	public CategoriaBO() {
		categoriaDAO = factory.getCategoriaDAO();
		produtoDAO = factory.getProdutoDAO();
	}
	
	public void alterarSituacao(Categoria pCategoria, Situacao pSituacao) {
		try {
			System.out.println("Altera a situacao dos produtos da Categoria : " + pCategoria.getCategoria());
			Collection<Produto> listaProdutoCategoria = new ArrayList<Produto>();	
			
			listaProdutoCategoria = (Collection<Produto>) pCategoria.getProdutos();
			for (Produto produto : listaProdutoCategoria){
				System.out.println("Alterou situação de : " + produto.getId() + " " + produto.getDescricao());
				produto.setSitProduto(pSituacao);
				produtoDAO.alterar(produto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
