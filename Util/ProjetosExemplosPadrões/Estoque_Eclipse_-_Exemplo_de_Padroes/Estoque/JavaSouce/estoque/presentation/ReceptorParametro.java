package estoque.presentation;

import javax.swing.WindowConstants;

import estoque.client.CadastrarParametro;


// Padrão Command : Receiver Command
public class ReceptorParametro {
	public void executar() {
		// O comando que será executado pode se substituído aqui sem afetar os demais
		System.out.print("\nChama o cadastro de Parametro!");
		CadastrarParametro cadastrarParametro = new CadastrarParametro();
		cadastrarParametro.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		cadastrarParametro.setVisible(true);

	}
}