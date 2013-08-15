package escola.client.command;

/**
 * Padrao Command : Command
 * 
 * @author Ricardo Azevedo
 * 
 */
public class CommandAluno implements Command {
	ReceptorAluno receptor;

	public CommandAluno(ReceptorAluno pReceptor) {
		this.receptor = pReceptor;
	}

	public void executar() {
		receptor.executar();
	}
}