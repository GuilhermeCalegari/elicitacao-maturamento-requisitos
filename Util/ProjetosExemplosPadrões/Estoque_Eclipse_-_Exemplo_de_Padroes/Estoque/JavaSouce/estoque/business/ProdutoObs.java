package estoque.business;

import java.util.Observable;
import java.util.Observer;

import estoque.business.bd.CategoriaBD;
import estoque.business.to.Categoria;
import estoque.business.to.Situacao;

// Padrão Observer : Observer
public class ProdutoObs {
	Categoria categoria	= new Categoria();
	CategoriaBD categoriaBD = new CategoriaBD();
	
	private ObservadorEstudo estudoObsrv = new ObservadorEstudo();
	private ObservadorAtivo ativoObsrv = new ObservadorAtivo();
	private ObservadorInativo inativoObsrv = new ObservadorInativo();

	public ProdutoObs(Categoria pCategoria) {
		categoria	=	pCategoria;	
	}

	// ConcreteObserver
	private class ObservadorEstudo implements Observer { // observa situação em estudo
		public void update(Observable observer, Object object) {
			categoriaBD.alterarSituacao(categoria, Situacao.estudo);
		}
	}
	
	// ConcreteObserver
	private class ObservadorAtivo implements Observer { // observa ativação
		public void update(Observable observer, Object object) {
			categoriaBD.alterarSituacao(categoria, Situacao.ativo);
		}
	}

	// ConcreteObserver
	private class ObservadorInativo implements Observer { // observa inativação
		public void update(Observable observer, Object object) {
			categoriaBD.alterarSituacao(categoria, Situacao.inativo);
		}
	}

	public Observer observadorEstudo() {
		return estudoObsrv;
	}
	
	public Observer observadorAtivo() {
		return ativoObsrv;
	}

	public Observer observadorInativo() {
		return inativoObsrv;
	}
}