package estoque.business;

import java.util.Observable;

import estoque.business.to.Situacao;

//Padrão Observer : Subject
public class SituacaoSubjectObs {
	private Situacao sitAnterior;

	private EstudoCategoria estudoCategoria = new EstudoCategoria();	
	private AtivaCategoria ativaCategoria = new AtivaCategoria();
	private InativaCategoria inativaCategoria = new InativaCategoria();

	public SituacaoSubjectObs(Situacao pSituacao) {
		sitAnterior	=	pSituacao;
	}

	public void estudo() { // Estudo 
		estudoCategoria.notifyObservers();
		estudoCategoria.estudo();
	}
	
	public void ativa() { // Ativa 
		ativaCategoria.notifyObservers();
		ativaCategoria.ativa();
	}

	public void inativa() { // Inativa Produtos
		inativaCategoria.notifyObservers();
		inativaCategoria.inativa();
	}

	public Observable notificadorEstudoCategoria() {
		return estudoCategoria;
	}
	
	public Observable notificadorAtivaCategoria() {
		return ativaCategoria;
	}

	public Observable notificadorInativaCategoria() {
		return inativaCategoria;
	}
	
//	 ConcreteSubject
	private class EstudoCategoria extends Observable {

		public void notifyObservers() {
			if (sitAnterior != Situacao.estudo) {
				setChanged();
				super.notifyObservers();
			}
		}

		public void estudo() {
			sitAnterior = Situacao.estudo;
		}
	}
	
//	 ConcreteSubject
	private class AtivaCategoria extends Observable {
		public void notifyObservers() {
			if (sitAnterior != Situacao.ativo) {
				setChanged();
				super.notifyObservers();
			}
		}

		public void ativa() {
			sitAnterior = Situacao.ativo;
		}
	}

//	 ConcreteSubject
	private class InativaCategoria extends Observable {

		public void notifyObservers() {
			if (sitAnterior != Situacao.inativo) {
				setChanged();
				super.notifyObservers();
			}
		}

		public void inativa() {
			sitAnterior = Situacao.inativo;
		}
	}
}