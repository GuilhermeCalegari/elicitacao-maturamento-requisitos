package escola.business.bo;

import escola.business.to.Aluno;
import escola.business.to.Curso;
import escola.business.to.Nota;
import escola.business.to.Turma;
import escola.integration.dao.EscolaPST;

/**
 * Implementa o padrao de projeto Business Object
 * 
 */
public class NotaBO {

	private EscolaPST fachadaPersistencia;

	private EscolaPST getFachadaPersistencia() {
		if (fachadaPersistencia == null) {
			fachadaPersistencia = EscolaPST.getInstance();
		}
		return fachadaPersistencia;
	}

	public NotaBO() {
	}

	// /////////////////////////////
	// METODOS PARA TRATAR NOTA ///
	// /////////////////////////////

	public void addNota(String idCurso, String idTurma, String raAluno,
			String notaAluno) {
		Curso curso = new Curso();
		curso.setIdCurso(idCurso);
		Curso cursoRetorno = getFachadaPersistencia().findCurso(curso);
		Turma turma = getFachadaPersistencia().findTurma(cursoRetorno, idTurma);
		Aluno aluno = new Aluno();
		aluno.setRA(raAluno);
		Aluno alunoRetorno = getFachadaPersistencia().findAluno(aluno);
		double num = Double.parseDouble(notaAluno);
		Nota nota = new Nota(num, alunoRetorno);
		getFachadaPersistencia().addNota(turma, nota);

	}

	public void updateNota(String idCurso, String idTurma, String raAluno,
			String notaAluno) {
		Curso curso = new Curso();
		curso.setIdCurso(idCurso);
		Curso cursoRetorno = getFachadaPersistencia().findCurso(curso);
		Turma turma = getFachadaPersistencia().findTurma(cursoRetorno, idTurma);
		Aluno aluno = new Aluno();
		aluno.setRA(raAluno);
		Aluno alunoRetorno = getFachadaPersistencia().findAluno(aluno);
		double num = Double.parseDouble(notaAluno);
		Nota nota = new Nota(num, alunoRetorno);
		getFachadaPersistencia().updateNota(turma, nota);
	}

	public void excludeNota(String idCurso, String idTurma, String raAluno,
			String notaAluno) {
		Curso curso = new Curso();
		curso.setIdCurso(idCurso);
		Curso cursoRetorno = getFachadaPersistencia().findCurso(curso);
		Turma turma = getFachadaPersistencia().findTurma(cursoRetorno, idTurma);
		Aluno aluno = new Aluno();
		aluno.setRA(raAluno);
		Aluno alunoRetorno = getFachadaPersistencia().findAluno(aluno);
		double num = Double.parseDouble(notaAluno);
		Nota nota = new Nota(num, alunoRetorno);
		getFachadaPersistencia().excludeNota(nota, turma);
	}

	public String[] getInfoNotas(String idCurso, String idTurma) {
		Curso curso = new Curso();
		curso.setIdCurso(idCurso);
		Curso cursoRetorno = getFachadaPersistencia().findCurso(curso);
		Turma turma = getFachadaPersistencia().findTurma(cursoRetorno, idTurma);
		return getFachadaPersistencia().getInfoNotas(turma.getNotas());
	}
}
