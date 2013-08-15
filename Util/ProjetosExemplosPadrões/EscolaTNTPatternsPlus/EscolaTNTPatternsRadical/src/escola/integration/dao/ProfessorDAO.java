package escola.integration.dao;

import java.util.ArrayList;

import escola.business.to.Professor;

/**
 * Padrão DAO (Interface)
 * Padrão Abstract Factory (Abstract Product)
 * @author Luiz Gustavo
 *
 */
public interface ProfessorDAO {
	public abstract void addProfessor(Professor professor);

	public abstract void updateProfessor(Professor prof);

	public abstract void excludeProfessor(Professor prof);

	public abstract Professor findProfessor(Professor prof);

	public abstract String[] getInfoProfessores();

	public abstract void setProfessores(ArrayList<Professor> professores);

	public abstract ArrayList<Professor> getProfessores();
}
