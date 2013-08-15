package escola.integration.dao;

import java.util.ArrayList;

import escola.business.to.Aluno;

/**
 * Padrão DAO (Interface)
 * Padrão Abstract Factory (Abstract Product)
 * @author Luiz Gustavo
 *
 */
public interface AlunoDAO {
	public abstract ArrayList<Aluno> getAlunos();

	public abstract void setAlunos(ArrayList<Aluno> alunos);

	public abstract void addAluno(Aluno aluno);

	public abstract Aluno findAluno(Aluno aluno);

	public abstract Aluno getAlunoPorName(String nomeAluno);

	public abstract void updateAluno(Aluno aluno);

	public abstract String[] getInfoAlunos();

	public abstract void excludeAluno(Aluno aluno);
}
