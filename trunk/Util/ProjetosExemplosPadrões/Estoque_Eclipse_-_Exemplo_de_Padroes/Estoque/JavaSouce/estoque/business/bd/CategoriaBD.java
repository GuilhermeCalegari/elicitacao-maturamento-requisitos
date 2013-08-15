package estoque.business.bd;

import java.util.Collection;

import estoque.business.bo.CategoriaBO;
import estoque.business.to.Categoria;
import estoque.business.to.Situacao;



// Padrão Business Delegate e Padrão Proxy (GoF)
public class CategoriaBD {

	private CategoriaBO categoriaBO = new CategoriaBO();

	public void salvar(Categoria categoria) {
		categoriaBO.salvar(categoria);
		
	}

	public void alterar(Categoria categoria) {
		categoriaBO.alterar(categoria);
		
	}

	public void apagar(Categoria categoria) {
		categoriaBO.apagar(categoria);
	}
	
	public Object consultarUltimo(Class<Categoria> name, String string) {
		return categoriaBO.consultarUltimo(name, string);
	}

	public Collection listar(Class<Categoria> name) {
		return categoriaBO.listar(name);
	}

	public Collection pesquisar(Class<Categoria> name, String string, String text, String string2) {
		return categoriaBO.pesquisar(name, string, text, string2);
	}

	public Categoria consultar(Class<Categoria> name, Integer id) {
		return (Categoria) categoriaBO.consultar(name, id);
	}
	
	public void alterarSituacao(Categoria categoria, Situacao situacao) {
		categoriaBO.alterarSituacao(categoria, situacao);
		
	}
}
