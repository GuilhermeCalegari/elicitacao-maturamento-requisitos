package escola.business.bo;

import java.util.ArrayList;
import java.util.Iterator;

import escola.business.to.Curso;
import escola.business.to.Professor;
import escola.business.to.Turma;
import escola.integration.dao.EscolaPST;

/**
 * Implementa o padrao de projeto Business Object
 * 
 */
public class ProfessorBO {

	private EscolaPST fachadaPersistencia;

	private EscolaPST getFachadaPersistencia() {
		if (fachadaPersistencia == null) {
			fachadaPersistencia = EscolaPST.getInstance();
		}
		return fachadaPersistencia;
	}

	public ProfessorBO() {
	}

	// ////////////////////////////////////
	// METODOS PARA TRATAR PROFESSORES //
	// ////////////////////////////////////

	public void addProfessor(Professor prof) {
		getFachadaPersistencia().addProfessor(prof);
	}

	public void updateProfessor(Professor prof) {
		getFachadaPersistencia().updateProfessor(prof);
	}

	public String[] updateListProfessor() {
		return getFachadaPersistencia().getInfoProfessores();
	}

	public String consultProfessor(String nomeProfessor) {
		Professor prof = new Professor(0, nomeProfessor, "");
		Professor professor = getFachadaPersistencia().findProfessor(prof);
		return professor.getNomePessoa() + " - " + professor.getEspecialidade();
	}

	public String[] getInfoProfessores() {
		return getFachadaPersistencia().getInfoProfessores();
	}

	public void excludeProfessor(String idProfessor) {
		int id = Integer.parseInt(idProfessor);
		Professor prof = new Professor();
		prof.setIdPessoa(id);
		ArrayList cursos = getFachadaPersistencia().getCursos();
		Professor professor;
		Iterator iteratorCursos;

		professor = getFachadaPersistencia().findProfessor(prof);
		if (professor != null) {
			iteratorCursos = cursos.iterator();
			while (iteratorCursos.hasNext()) {
				Curso curso = (Curso) iteratorCursos.next();
				ArrayList turmas = curso.getTurmas();
				Iterator iterator = turmas.iterator();

				while (iterator.hasNext()) {
					Turma turma = (Turma) iterator.next();
					if (turma.getProfessor().getIdPessoa() == prof
							.getIdPessoa()) {
						getFachadaPersistencia().excludeTurma(curso, turma);
						turmas = curso.getTurmas();
						iterator = turmas.iterator();
					}
				}
			}
			getFachadaPersistencia().excludeProfessor(professor);
		}
	}
}
