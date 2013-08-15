package escola.integration.dao.pst;

import java.util.ArrayList;
import java.util.Iterator;

import escola.business.to.Curso;
import escola.business.to.Professor;
import escola.business.to.Turma;
import escola.integration.dao.CursoDAO;

/**
 * Padrão Abstract Factory (Concrete Product) 
 * 
 * @author Luiz Gustavo
 */
public class CursoMemDAO implements CursoDAO {

	private ArrayList<Curso> cursos;
	private ArrayList<Turma> turmas;

	public CursoMemDAO() {
		cursos = new ArrayList<Curso>();
		turmas = new ArrayList<Turma>();
		
		Curso c1 = new Curso("JAVA B", "JAVA BASICO");
		Curso c2 = new Curso("BANCO P", "BANCO POSTGRE BASICO");
		Curso c3 = new Curso("LINUX I", "LINUX BASICO");
		
		cursos.add(c1);
		cursos.add(c2);
		cursos.add(c3);
	}

	public void addCurso(Curso curso) {
		this.cursos.add(curso);
	}

	public void addTurma(Curso curso, Turma turma) {
		turmas = curso.getTurmas();
		turmas.add(turma);
		curso.setTurmas(turmas);
	}

	public void updateCurso(Curso curso) {
		Curso cursoUpdate = findCurso(curso);
		if (cursoUpdate != null) {
			cursoUpdate.setNomeCurso(curso.getNomeCurso());
		}
	}

	public void updateTurma(Curso curso, Turma turma) {
		Curso cursoRetorno = findCurso(curso);
		if (cursoRetorno != null) {
			Turma turmaRetorno = this.findTurma(cursoRetorno, turma
					.getIdTurma());
			if (turmaRetorno != null) {
				turmaRetorno.setPeriodo(turma.getPeriodo());
				turmaRetorno.setProfessor(turma.getProfessor());
			}
		}
	}

	public void excludeCurso(Curso curso) {
		Curso cursoRetorno = findCurso(curso);
		if (cursoRetorno != null) {
			cursos.remove(cursoRetorno);
		}
	}

	public void excludeTurma(Curso curso, Turma turma) {
		Curso cursoRetorno = findCurso(curso);
		if (cursoRetorno != null) {
			Turma turmaRetorno = this.findTurma(cursoRetorno, turma
					.getIdTurma());
			if (turmaRetorno != null) {
				this.removeTurma(cursoRetorno, turmaRetorno);
			}
		}
	}

	public Curso findCurso(Curso curso) {
		Curso cursoPercorre, cursoRetorno;
		Iterator iteratorCursos;

		cursoRetorno = null;
		iteratorCursos = cursos.iterator();
		while (iteratorCursos.hasNext()) {
			cursoPercorre = (Curso) iteratorCursos.next();
			if (cursoPercorre.getIdCurso().equals(curso.getIdCurso()))
				cursoRetorno = cursoPercorre;
		}
		return cursoRetorno;
	}

	public String[] getInfoCursos() {
		Curso curso;
		String[] info = new String[cursos.size()];
		Iterator iteratorCursos;
		iteratorCursos = cursos.iterator();
		int i = 0;
		while (iteratorCursos.hasNext()) {
			curso = (Curso) iteratorCursos.next();
			info[i] = curso.getIdCurso() + " - " + curso.getNomeCurso().trim();
			i++;
		}
		return info;
	}

	public String[] getInfoTurmas(Curso curso) {
		ArrayList array = curso.getTurmas();
		String[] info = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			Turma turma = (Turma) array.get(i);
			info[i] = curso.getIdCurso() + " - " + curso.getNomeCurso() + " - "
					+ turma.getIdTurma() + " - "
					+ turma.getProfessor().getIdPessoa() + " - "
					+ turma.getProfessor().getNomePessoa() + " - "
					+ turma.getPeriodo();
		}
		return info;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	public Turma findTurma(Curso curso, String idTurma) {
		Turma turmaLaco, turmaRetorno;

		turmaRetorno = null;
		Iterator iteratorTurmas = curso.getTurmas().iterator();
		while (iteratorTurmas.hasNext()) {
			turmaLaco = (Turma) iteratorTurmas.next();
			if (turmaLaco.getIdTurma().equals(idTurma))
				turmaRetorno = turmaLaco;
		}
		return turmaRetorno;
	}

	public void addTurma(Curso curso, String cod, Professor prof, String per) {
		Turma turma = new Turma(cod, prof, per);
		curso.getTurmas().add(turma);
	}

	public void removeTurma(Curso curso, Turma turma) {
		curso.getTurmas().remove(turma);
	}
}
