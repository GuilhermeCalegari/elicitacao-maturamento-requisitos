package escola.client.command;

import javax.swing.WindowConstants;

import escola.presentation.gui.TurmaGUI;

/**
 * Padrao Command : Receiver Command
 * 
 * @author Ricardo Azevedo
 * 
 */
public class ReceptorTurma {
	public void executar() {
		// O comando que sera executado pode se substituido aqui sem afetar os
		// demais
		TurmaGUI turmaGUI = new TurmaGUI();
		turmaGUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		turmaGUI.setVisible(true);

	}

}