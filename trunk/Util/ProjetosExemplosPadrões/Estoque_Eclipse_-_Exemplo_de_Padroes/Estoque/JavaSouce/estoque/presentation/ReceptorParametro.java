package estoque.presentation;

import javax.swing.WindowConstants;

import estoque.client.CadastrarParametro;


// Padr�o Command : Receiver Command
public class ReceptorParametro {
	public void executar() {
		// O comando que ser� executado pode se substitu�do aqui sem afetar os demais
		System.out.print("\nChama o cadastro de Parametro!");
		CadastrarParametro cadastrarParametro = new CadastrarParametro();
		cadastrarParametro.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		cadastrarParametro.setVisible(true);

	}
}