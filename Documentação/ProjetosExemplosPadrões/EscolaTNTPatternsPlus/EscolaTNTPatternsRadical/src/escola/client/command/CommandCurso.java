package escola.client.command;

/**
 * Padrao Command : ConcreteCommand
 * 
 * @author Ricardo Azevedo
 * 
 */
public class CommandCurso implements Command {
	ReceptorCurso receptor;

	public CommandCurso(ReceptorCurso pReceptor) {
		this.receptor = pReceptor;
	}

	public void executar() {
		receptor.executar();
	}
}