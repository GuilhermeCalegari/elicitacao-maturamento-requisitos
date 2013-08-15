package estoque.business.bd;

import java.math.BigDecimal;
import java.util.Collection;

import estoque.business.bo.ProdutoBO;
import estoque.business.to.Produto;



// Padrão Business Delegate e Padrão Proxy (GoF)
public class ProdutoBD {

	private static ProdutoBO produtoBO = new ProdutoBO();

	public void salvar(Produto produto) {
		produtoBO.salvar(produto);
		
	}

	public void alterar(Produto produto) {
		produtoBO.alterar(produto);
		
	}

	public void apagar(Produto produto) {
		produtoBO.apagar(produto);
	}
	
	public Object consultarUltimo(Class<Produto> name, String string) {
		return produtoBO.consultarUltimo(name, string);
	}

	public Collection listar(Class<Produto> name) {
		return produtoBO.listar(name);
	}

	public Collection pesquisar(Class<Produto> name, String string, String text, String string2) {
		return produtoBO.pesquisar(name, string, text, string2);
	}

	public Produto consultar(Class<Produto> name, Integer id) {
		return (Produto) produtoBO.consultar(name, id);
	}
	
	public BigDecimal calcularPrecoVenda(BigDecimal pPrecoCusto) {
		return produtoBO.calcularPrecoVenda(pPrecoCusto);
	}
}
