package escola.client.command;

import javax.swing.WindowConstants;

import escola.presentation.gui.CursoGUI;

/**
 * Padrao Command : Receiver Command
 * 
 * @author Ricardo Azevedo
 * 
 */
public class ReceptorCurso {
	public void executar() {
		// O comando que sera executado pode se substituido aqui sem afetar os
		// demais
		CursoGUI cursoGUI = new CursoGUI();
		cursoGUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		cursoGUI.setVisible(true);

	}

}