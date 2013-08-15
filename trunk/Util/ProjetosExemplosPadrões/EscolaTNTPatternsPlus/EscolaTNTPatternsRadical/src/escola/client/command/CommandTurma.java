package escola.client.command;

/**
 * Padrao Command : ConcreteCommand
 * 
 * @author Ricardo Azevedo
 * 
 */
public class CommandTurma implements Command {
	ReceptorTurma receptor;

	public CommandTurma(ReceptorTurma pReceptor) {
		this.receptor = pReceptor;
	}

	public void executar() {
		receptor.executar();
	}
}