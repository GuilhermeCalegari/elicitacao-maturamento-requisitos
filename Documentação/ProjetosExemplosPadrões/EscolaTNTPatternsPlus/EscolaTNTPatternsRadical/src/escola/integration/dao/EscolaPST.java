package escola.integration.dao;

import java.util.ArrayList;

import escola.business.to.Aluno;
import escola.business.to.Curso;
import escola.business.to.Nota;
import escola.business.to.Professor;
import escola.business.to.Turma;
import escola.integration.dao.pst.MemoryPersistenceFactory;

/**
 * Fachada para a camada de persistencia. Fachadas geralmente sao Singletons. * 
 * Padrão Abstract Factory (Client)
 * 
 * @author Luiz Gustavo
 */
public final class EscolaPST implements AlunoDAO, CursoDAO, ProfessorDAO,
		TurmaDAO {

	private static EscolaPST instance = null;
	private DAOFactory factory;

	private ProfessorDAO professorDAO;
	private CursoDAO cursoDAO;
	private AlunoDAO alunoDAO;
	private TurmaDAO turmaDAO;

	private EscolaPST() {
		// neste momento e decidido qual implementacao da factory sera usada
		factory = MemoryPersistenceFactory.getInstance();
	}

	public static EscolaPST getInstance() {
		if (instance == null) {
			instance = new EscolaPST();
		}
		return instance;
	}

	public ProfessorDAO getProfessorDAO() {
		if (professorDAO == null) {
			professorDAO = factory.getProfessorDAO();
		}
		return professorDAO;
	}

	public CursoDAO getCursoDAO() {
		if (cursoDAO == null) {
			cursoDAO = factory.getCursoDAO();
		}
		return cursoDAO;
	}

	public AlunoDAO getAlunoDAO() {
		if (alunoDAO == null) {
			alunoDAO = factory.getAlunoDAO();
		}
		return alunoDAO;
	}

	public TurmaDAO getTurmaDAO() {
		if (turmaDAO == null) {
			turmaDAO = factory.getTurmaDAO();
		}
		return turmaDAO;
	}

	@Override
	public void addAluno(Aluno aluno) {
		getAlunoDAO().addAluno(aluno);
	}

	@Override
	public void excludeAluno(Aluno aluno) {
		getAlunoDAO().excludeAluno(aluno);
	}

	@Override
	public Aluno findAluno(Aluno aluno) {
		return getAlunoDAO().findAluno(aluno);
	}

	@Override
	public Aluno getAlunoPorName(String nomeAluno) {
		return getAlunoDAO().getAlunoPorName(nomeAluno);
	}

	@Override
	public ArrayList<Aluno> getAlunos() {
		return getAlunoDAO().getAlunos();
	}

	@Override
	public String[] getInfoAlunos() {
		return getAlunoDAO().getInfoAlunos();
	}

	@Override
	public void setAlunos(ArrayList<Aluno> alunos) {
		getAlunoDAO().setAlunos(alunos);
	}

	@Override
	public void updateAluno(Aluno aluno) {
		getAlunoDAO().updateAluno(aluno);
	}

	@Override
	public void addCurso(Curso curso) {
		getCursoDAO().addCurso(curso);
	}

	@Override
	public void addTurma(Curso curso, Turma turma) {
		getCursoDAO().addTurma(curso, turma);
	}

	@Override
	public void addTurma(Curso curso, String cod, Professor prof, String per) {
		getCursoDAO().addTurma(curso, cod, prof, per);
	}

	@Override
	public void excludeCurso(Curso curso) {
		getCursoDAO().excludeCurso(curso);
	}

	@Override
	public void excludeTurma(Curso curso, Turma turma) {
		getCursoDAO().excludeTurma(curso, turma);
	}

	@Override
	public Curso findCurso(Curso curso) {
		return getCursoDAO().findCurso(curso);
	}

	@Override
	public Turma findTurma(Curso curso, String idTurma) {
		return getCursoDAO().findTurma(curso, idTurma);
	}

	@Override
	public ArrayList<Curso> getCursos() {
		return getCursoDAO().getCursos();
	}

	@Override
	public String[] getInfoCursos() {
		return getCursoDAO().getInfoCursos();
	}

	@Override
	public String[] getInfoTurmas(Curso curso) {
		return getCursoDAO().getInfoTurmas(curso);
	}

	@Override
	public void removeTurma(Curso curso, Turma turma) {
		getCursoDAO().removeTurma(curso, turma);
	}

	@Override
	public void setCursos(ArrayList<Curso> cursos) {
		getCursoDAO().setCursos(cursos);
	}

	@Override
	public void updateCurso(Curso curso) {
		getCursoDAO().updateCurso(curso);
	}

	@Override
	public void updateTurma(Curso curso, Turma turma) {
		getCursoDAO().updateTurma(curso, turma);
	}

	@Override
	public void addProfessor(Professor professor) {
		getProfessorDAO().addProfessor(professor);
	}

	@Override
	public void excludeProfessor(Professor prof) {
		getProfessorDAO().excludeProfessor(prof);
	}

	@Override
	public Professor findProfessor(Professor prof) {
		return getProfessorDAO().findProfessor(prof);
	}

	@Override
	public String[] getInfoProfessores() {
		return getProfessorDAO().getInfoProfessores();
	}

	@Override
	public ArrayList<Professor> getProfessores() {
		return getProfessorDAO().getProfessores();
	}

	@Override
	public void setProfessores(ArrayList<Professor> professores) {
		getProfessorDAO().setProfessores(professores);
	}

	@Override
	public void updateProfessor(Professor prof) {
		getProfessorDAO().updateProfessor(prof);
	}

	@Override
	public void addNota(Turma turma, Nota nota) {
		getTurmaDAO().addNota(turma, nota);
	}

	@Override
	public void addNota(Turma turma, double notaAlu, Aluno aluno) {
		getTurmaDAO().addNota(turma, notaAlu, aluno);
	}

	@Override
	public void excludeNota(Nota nota, Turma turma) {
		getTurmaDAO().excludeNota(nota, turma);
	}

	@Override
	public void excludeNotaTurma(Aluno aluno, Turma turma) {
		getTurmaDAO().excludeNotaTurma(aluno, turma);
	}

	@Override
	public Nota findNota(Turma turma, Aluno aluno) {
		return getTurmaDAO().findNota(turma, aluno);
	}

	@Override
	public String[] getInfoNotas(ArrayList notas) {
		return getTurmaDAO().getInfoNotas(notas);
	}

	@Override
	public ArrayList<Turma> getTurmas() {
		return getTurmaDAO().getTurmas();
	}

	@Override
	public void setTurmas(ArrayList<Turma> turmas) {
		getTurmaDAO().setTurmas(turmas);
	}

	@Override
	public void updateNota(Turma turma, Nota nota) {
		getTurmaDAO().updateNota(turma, nota);
	}
}
