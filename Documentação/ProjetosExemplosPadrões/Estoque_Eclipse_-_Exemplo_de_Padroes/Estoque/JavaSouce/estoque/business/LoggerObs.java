package estoque.business;

import java.util.Observable;
import java.util.Observer;

import estoque.business.to.Categoria;
import estoque.util.GlobalParameter;

// Padr�o Observer : Observer
public class LoggerObs {
	private	Categoria categoria	= new Categoria();
	private	GlobalParameter globalParam = GlobalParameter.getInstance();	// Pega a inst�ncia de Parametros
	private ObservadorEstudo estudoObsrv = new ObservadorEstudo();
	private ObservadorAtivo ativoObsrv = new ObservadorAtivo();
	private ObservadorInativo inativoObsrv = new ObservadorInativo();

	public LoggerObs(Categoria pCategoria) {
		categoria	=	pCategoria;	
	}

	// ConcreteObserver
	private class ObservadorEstudo implements Observer { // observa situa��o em estudo
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Estudo");
			GlobalParameter.logger.warn("Categoria: " + categoria.getId() + " - "
					+ categoria.getCategoria() + " foi colocada em estudo pelo usuário "
					+ globalParam.getUsuario());
		}
	}
	
	// ConcreteObserver
	private class ObservadorAtivo implements Observer { // observa ativa��o
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Ativo");
			GlobalParameter.logger.warn("Categoria: " + categoria.getId() + " - "
					+ categoria.getCategoria() + " foi ativada pelo usu�rio "
					+ globalParam.getUsuario());
		}
	}

	// ConcreteObserver
	private class ObservadorInativo implements Observer { // observa inativa��o
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Inativo");
			GlobalParameter.logger.warn("Categoria: " + categoria.getId() + " - "
					+ categoria.getCategoria() + " foi inativada pelo usu�rio "
					+ globalParam.getUsuario());
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