package estoque.presentation;

import javax.swing.WindowConstants;

import estoque.client.ListarCategoria;


// Padrão Command : Receiver Command
public class ReceptorCategoria {
	public void executar() {
		// O comando que será executado pode se substituído aqui sem afetar os demais
		System.out.print("\nChama o cadastro de Categoria!");
		ListarCategoria listarCategoria = new ListarCategoria();
		listarCategoria.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		listarCategoria.setVisible(true);

	}
}