package escola.client.command;

import javax.swing.WindowConstants;

import escola.presentation.gui.ProfessorGUI;

/**
 * Padrao Command : Receiver Command
 * 
 * @author Ricardo Azevedo
 * 
 */
public class ReceptorProfessor {
	public void executar() {
		// O comando que sera executado pode se substituido aqui sem afetar os
		// demais
		ProfessorGUI professorGUI = new ProfessorGUI();
		professorGUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		professorGUI.setVisible(true);

	}

}