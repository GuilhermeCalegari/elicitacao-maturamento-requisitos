package escola.business.bo;

import escola.business.to.Curso;
import escola.business.to.Professor;
import escola.business.to.Turma;
import escola.integration.dao.EscolaPST;

/**
 * Implementa o padrao de projeto Business Object
 * 
 */
public class TurmaBO {

	private EscolaPST fachadaPersistencia;

	private EscolaPST getFachadaPersistencia() {
		if (fachadaPersistencia == null) {
			fachadaPersistencia = EscolaPST.getInstance();
		}
		return fachadaPersistencia;
	}

	public TurmaBO() {
	}

	// /////////////////////////////
	// METODOS PARA TRATAR TURMA //
	// /////////////////////////////

	public void addTurma(String idCurso, String idTurma, String idProfessor,
			String periodo) {
		int idProf = Integer.parseInt(idProfessor);
		Professor prof = new Professor();
		prof.setIdPessoa(idProf);
		prof = getFachadaPersistencia().findProfessor(prof);
		Curso curso = new Curso();
		curso.setIdCurso(idCurso);
		curso = getFachadaPersistencia().findCurso(curso);
		Turma turma = new Turma(idTurma, prof, periodo);
		getFachadaPersistencia().addTurma(curso, turma);
	}

	public void updateTurma(String idCurso, String idTurma, String idProfessor,
			String periodo) {
		int idProf = Integer.parseInt(idProfessor);
		Professor prof = new Professor();
		prof.setIdPessoa(idProf);
		prof = getFachadaPersistencia().findProfessor(prof);
		Curso curso = new Curso();
		curso.setIdCurso(idCurso);
		Turma turma = new Turma(idTurma, prof, periodo);
		getFachadaPersistencia().updateTurma(curso, turma);
	}

	public void excludeTurma(String idCurso, String idTurma) {
		Curso curso = new Curso();
		curso.setIdCurso(idCurso);
		Turma turma = new Turma();
		turma.setIdTurma(idTurma);
		getFachadaPersistencia().excludeTurma(curso, turma);
	}

	public String[] getInfoTurmas(String idCurso) {
		Curso curso = new Curso(idCurso, "");
		curso = getFachadaPersistencia().findCurso(curso);
		return getFachadaPersistencia().getInfoTurmas(curso);
	}
}
