package escola.presentation.bd;

import escola.business.bo.TurmaBO;

/**
 * 
 * @author Ricardo Azevedo 
 * Implementacao do padrao de projeto Business Delegate
 */
public class TurmaBD {

	// Business Object
	private TurmaBO turmaBO;

	public TurmaBD() {
		turmaBO = new TurmaBO();
	}

	// /////////////////////////////
	// METODOS PARA TRATAR TURMA //
	// /////////////////////////////
	public void addTurma(String idCurso, String idTurma, String idProfessor,
			String periodo) {
		turmaBO.addTurma(idCurso, idTurma, idProfessor, periodo);
	}

	public void updateTurma(String idCurso, String idTurma, String idProfessor,
			String periodo) {
		turmaBO.updateTurma(idCurso, idTurma, idProfessor, periodo);
	}

	public void excludeTurma(String idCurso, String idTurma) {
		turmaBO.excludeTurma(idCurso, idTurma);
	}

	public String[] getInfoTurmas(String idCurso) {
		return turmaBO.getInfoTurmas(idCurso);
	}

}
