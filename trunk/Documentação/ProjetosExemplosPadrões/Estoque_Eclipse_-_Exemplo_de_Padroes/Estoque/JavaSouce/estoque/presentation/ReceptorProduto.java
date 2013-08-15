package estoque.presentation;

import javax.swing.WindowConstants;

import estoque.client.ListarProduto;


// Padrão Command : Receiver Command
public class ReceptorProduto {
	public void executar() {
		// O comando que será executado pode se substituído aqui sem afetar os demais
		System.out.print("\nChama o cadastro de Produto!");
		ListarProduto listarProduto = new ListarProduto();
		listarProduto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		listarProduto.setVisible(true);

	}
}