package escola.client.command;

/**
 * Padrao Command : ConcreteCommand
 * 
 * @author Ricardo Azevedo
 * 
 */
public class CommandNota implements Command {
	ReceptorNota receptor;

	public CommandNota(ReceptorNota pReceptor) {
		this.receptor = pReceptor;
	}

	public void executar() {
		receptor.executar();
	}
}