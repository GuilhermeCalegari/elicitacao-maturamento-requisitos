package escola.client.command;

import javax.swing.WindowConstants;

import escola.presentation.gui.AlunoGUI;

/**
 * Padrao Command : Receiver Command
 * 
 * @author Ricardo Azevedo
 * 
 */
public class ReceptorAluno {
	public void executar() {
		// O comando que sera executado pode se substituido aqui sem afetar os
		// demais
		AlunoGUI alunoGUI = new AlunoGUI();
		alunoGUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		alunoGUI.setVisible(true);

	}
}