package escola.integration.dao.pstdb;

import escola.integration.dao.AlunoDAO;
import escola.integration.dao.CursoDAO;
import escola.integration.dao.DAOFactory;
import escola.integration.dao.ProfessorDAO;
import escola.integration.dao.TurmaDAO;

/**
 * Padrão Abstract Factory (Concrete Factory) 
 * AS classes factory concretas geralemnte sao implementadas como Singletons.
 * 
 * @author Luiz Gustavo
 */
public class DatabasePersistenceFactory implements DAOFactory {

	private static DatabasePersistenceFactory instance;

	private DatabasePersistenceFactory() {
	}

	public static DatabasePersistenceFactory getInstance() {
		if (instance == null) {
			instance = new DatabasePersistenceFactory();
		}
		return instance;
	}

	@Override
	public AlunoDAO getAlunoDAO() {
		return new AlunoDbDAO();
	}

	@Override
	public CursoDAO getCursoDAO() {
		return new CursoDbDAO();
	}

	@Override
	public ProfessorDAO getProfessorDAO() {
		return new ProfessorDbDAO();
	}

	@Override
	public TurmaDAO getTurmaDAO() {
		return new TurmaDbDAO();
	}
}
