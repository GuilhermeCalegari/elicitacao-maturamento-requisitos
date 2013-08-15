package estoque.presentation;

import javax.swing.WindowConstants;

import estoque.client.ListarProduto;


// Padr�o Command : Receiver Command
public class ReceptorProduto {
	public void executar() {
		// O comando que ser� executado pode se substitu�do aqui sem afetar os demais
		System.out.print("\nChama o cadastro de Produto!");
		ListarProduto listarProduto = new ListarProduto();
		listarProduto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		listarProduto.setVisible(true);

	}
}