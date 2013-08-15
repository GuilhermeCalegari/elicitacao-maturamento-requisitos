package escola.client.command;

/**
 * Padrao Command : ConcreteCommand
 * 
 * @author Ricardo Azevedo
 * 
 */
public class CommandProfessor implements Command {
	ReceptorProfessor receptor;

	public CommandProfessor(ReceptorProfessor pReceptor) {
		this.receptor = pReceptor;
	}

	public void executar() {
		receptor.executar();
	}
}