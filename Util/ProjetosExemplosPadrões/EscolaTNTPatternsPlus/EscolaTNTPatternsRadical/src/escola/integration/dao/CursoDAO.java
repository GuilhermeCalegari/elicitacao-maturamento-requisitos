package escola.integration.dao;

import java.util.ArrayList;

import escola.business.to.Curso;
import escola.business.to.Professor;
import escola.business.to.Turma;

/**
 * Padrão DAO (Interface)
 * Padrão Abstract Factory (Abstract Product)
 * @author Luiz Gustavo
 *
 */
public interface CursoDAO {
	public abstract void addCurso(Curso curso);

	public abstract void addTurma(Curso curso, Turma turma);

	public abstract void updateCurso(Curso curso);

	public abstract void updateTurma(Curso curso, Turma turma);

	public void excludeCurso(Curso curso);

	public abstract void excludeTurma(Curso curso, Turma turma);

	public abstract Curso findCurso(Curso curso);

	public abstract String[] getInfoCursos();

	public abstract String[] getInfoTurmas(Curso curso);

	public abstract ArrayList<Curso> getCursos();

	public abstract void setCursos(ArrayList<Curso> cursos);

	public abstract Turma findTurma(Curso curso, String idTurma);

	public abstract void addTurma(Curso curso, String cod, Professor prof,
			String per);

	public abstract void removeTurma(Curso curso, Turma turma);
}
