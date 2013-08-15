package estoque.presentation;

// Padr�o Command : ConcreteCommand
public class CommandCategoria implements Command {
	ReceptorCategoria receptor;

	public CommandCategoria(ReceptorCategoria pReceptor) {
		this.receptor = pReceptor;
	}

	public void executar() {
		receptor.executar();
	}
}