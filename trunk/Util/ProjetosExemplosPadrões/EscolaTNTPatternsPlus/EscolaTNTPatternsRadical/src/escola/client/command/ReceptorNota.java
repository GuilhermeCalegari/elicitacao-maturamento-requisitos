package escola.client.command;

import javax.swing.WindowConstants;

import escola.presentation.gui.NotaGUI;

/**
 * Padrao Command : Receiver Command
 * 
 * @author Ricardo Azevedo
 * 
 */
public class ReceptorNota {
	public void executar() {
		// O comando que sera executado pode se substituido aqui sem afetar os
		// demais
		NotaGUI notaGUI = new NotaGUI();
		notaGUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		notaGUI.setVisible(true);

	}

}