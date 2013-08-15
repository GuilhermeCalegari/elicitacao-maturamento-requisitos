package escola.integration.dao.pst;

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
public class MemoryPersistenceFactory implements DAOFactory {

	private static MemoryPersistenceFactory instance;

	private MemoryPersistenceFactory() {
	}

	public static MemoryPersistenceFactory getInstance() {
		if (instance == null) {
			instance = new MemoryPersistenceFactory();
		}
		return instance;
	}

	@Override
	public AlunoDAO getAlunoDAO() {
		return new AlunoMemDAO();
	}

	@Override
	public CursoDAO getCursoDAO() {
		return new CursoMemDAO();
	}

	@Override
	public ProfessorDAO getProfessorDAO() {
		return new ProfessorMemDAO();
	}

	@Override
	public TurmaDAO getTurmaDAO() {
		return new TurmaMemDAO();
	}

}
