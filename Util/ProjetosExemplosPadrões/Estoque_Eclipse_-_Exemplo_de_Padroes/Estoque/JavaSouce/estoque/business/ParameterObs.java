package estoque.business;

import java.util.Observable;
import java.util.Observer;

import estoque.business.to.Categoria;
import estoque.util.GlobalParameter;

// Padrão Observer : Observer
public class ParameterObs {
	Categoria categoria	= new Categoria();
	GlobalParameter globalParam = GlobalParameter.getInstance();	// Pega a instância de Parametros
	
	private ObservadorEstudo estudoObsrv = new ObservadorEstudo();
	private ObservadorAtivo ativoObsrv = new ObservadorAtivo();
	private ObservadorInativo inativoObsrv = new ObservadorInativo();

	public ParameterObs(Categoria pCategoria) {
		categoria	=	pCategoria;	
	}

	// ConcreteObserver
	private class ObservadorEstudo implements Observer { // observa situação em estudo
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Estudo");
			globalParam.removeCategoriaInativa(categoria);
			globalParam.imprimeValores(globalParam);
		}
	}
	
	// ConcreteObserver
	private class ObservadorAtivo implements Observer { // observa ativação
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Ativo");
			globalParam.removeCategoriaInativa(categoria);
			globalParam.imprimeValores(globalParam);
		}
	}

	// ConcreteObserver
	private class ObservadorInativo implements Observer { // observa inativação
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Inativo");
			globalParam.adicionaCategoriaInativa(categoria);
			globalParam.imprimeValores(globalParam);
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