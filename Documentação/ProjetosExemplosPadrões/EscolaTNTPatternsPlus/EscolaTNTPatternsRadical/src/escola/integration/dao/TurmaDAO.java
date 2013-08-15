package escola.integration.dao;

import java.util.ArrayList;

import escola.business.to.Aluno;
import escola.business.to.Nota;
import escola.business.to.Turma;

/**
 * Padrão DAO (Interface)
 * Padrão Abstract Factory (Abstract Product)
 * @author Luiz Gustavo
 *
 */
public interface TurmaDAO {
	public abstract ArrayList<Turma> getTurmas();

	public abstract void setTurmas(ArrayList<Turma> turmas);

	public abstract void addNota(Turma turma, Nota nota);

	public abstract void addNota(Turma turma, double notaAlu, Aluno aluno);

	public abstract void updateNota(Turma turma, Nota nota);

	public abstract void excludeNota(Nota nota, Turma turma);

	public String[] getInfoNotas(ArrayList notas);

	public abstract Nota findNota(Turma turma, Aluno aluno);

	public abstract void excludeNotaTurma(Aluno aluno, Turma turma);
}
