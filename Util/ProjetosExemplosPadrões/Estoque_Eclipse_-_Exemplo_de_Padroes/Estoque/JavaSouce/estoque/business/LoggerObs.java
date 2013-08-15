package estoque.business;

import java.util.Observable;
import java.util.Observer;

import estoque.business.to.Categoria;
import estoque.util.GlobalParameter;

// Padrão Observer : Observer
public class LoggerObs {
	private	Categoria categoria	= new Categoria();
	private	GlobalParameter globalParam = GlobalParameter.getInstance();	// Pega a instância de Parametros
	private ObservadorEstudo estudoObsrv = new ObservadorEstudo();
	private ObservadorAtivo ativoObsrv = new ObservadorAtivo();
	private ObservadorInativo inativoObsrv = new ObservadorInativo();

	public LoggerObs(Categoria pCategoria) {
		categoria	=	pCategoria;	
	}

	// ConcreteObserver
	private class ObservadorEstudo implements Observer { // observa situação em estudo
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Estudo");
			GlobalParameter.logger.warn("Categoria: " + categoria.getId() + " - "
					+ categoria.getCategoria() + " foi colocada em estudo pelo usuÃ¡rio "
					+ globalParam.getUsuario());
		}
	}
	
	// ConcreteObserver
	private class ObservadorAtivo implements Observer { // observa ativação
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Ativo");
			GlobalParameter.logger.warn("Categoria: " + categoria.getId() + " - "
					+ categoria.getCategoria() + " foi ativada pelo usuário "
					+ globalParam.getUsuario());
		}
	}

	// ConcreteObserver
	private class ObservadorInativo implements Observer { // observa inativação
		public void update(Observable observer, Object object) {
			System.out.println(this.getClass().getName() + " Situacao Inativo");
			GlobalParameter.logger.warn("Categoria: " + categoria.getId() + " - "
					+ categoria.getCategoria() + " foi inativada pelo usuário "
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