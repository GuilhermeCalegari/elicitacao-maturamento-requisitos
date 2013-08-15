package escola.presentation.bd;

import escola.business.bo.NotaBO;

/**
 * @author Ricardo Azevedo
 * Implementa o padraoo de projeto Business Delegate
 */
public class NotaBD {

    // Business Object
    private NotaBO notaBO;

    public NotaBD() {
        notaBO = new NotaBO();
    }

	// /////////////////////////////
	// METODOS PARA TRATAR NOTA ///
	// /////////////////////////////

	public void addNota(String idCurso, String idTurma, String raAluno,
			String notaAluno) {

		notaBO.addNota(idCurso, idTurma, raAluno, notaAluno);
	}

	public void updateNota(String idCurso, String idTurma, String raAluno,
			String notaAluno) {
		notaBO.updateNota(idCurso, idTurma, raAluno, notaAluno);
	}

	public void excludeNota(String idCurso, String idTurma, String raAluno,
			String notaAluno) {
		notaBO.excludeNota(idCurso, idTurma, raAluno, notaAluno);
	}

	public String[] getInfoNotas(String idCurso, String idTurma) {
		return notaBO.getInfoNotas(idCurso, idTurma);
	}
}
