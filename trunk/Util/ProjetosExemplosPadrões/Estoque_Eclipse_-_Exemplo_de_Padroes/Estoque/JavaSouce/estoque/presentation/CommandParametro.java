package estoque.presentation;

//Padrão Command : ConcreteCommand
public class CommandParametro implements Command {
	ReceptorParametro receptor;

	public CommandParametro(ReceptorParametro pReceptor) {
		this.receptor = pReceptor;
	}

	public void executar() {
		receptor.executar();
	}
}