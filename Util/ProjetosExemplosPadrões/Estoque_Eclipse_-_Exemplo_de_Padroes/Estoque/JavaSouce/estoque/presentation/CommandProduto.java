package estoque.presentation;

// Padrão Command : ConcreteCommand
public class CommandProduto implements Command {
	ReceptorProduto receptor;

	public CommandProduto(ReceptorProduto pReceptor) {
		this.receptor = pReceptor;
	}

	public void executar() {
		receptor.executar();
	}
}