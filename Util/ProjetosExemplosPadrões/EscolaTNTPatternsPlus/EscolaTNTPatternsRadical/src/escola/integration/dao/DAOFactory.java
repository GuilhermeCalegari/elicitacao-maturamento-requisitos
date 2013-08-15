package escola.integration.dao;

/**
 * Padr�o Abstract Factory (Abstract Factory)
 * @author Luiz Gustavo
 */
public interface DAOFactory {

	public AlunoDAO getAlunoDAO();

	public CursoDAO getCursoDAO();

	public ProfessorDAO getProfessorDAO();

	public TurmaDAO getTurmaDAO();
}
