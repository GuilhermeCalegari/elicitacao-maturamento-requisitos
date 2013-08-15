package escola.integration.dao.pstdb;

import java.util.ArrayList;
import java.util.Iterator;

import escola.business.to.Aluno;
import escola.business.to.Nota;
import escola.business.to.Turma;
import escola.integration.dao.TurmaDAO;

/**
 * Padrão Abstract Factory (Concrete Product) 
 * 
 * @author Luiz Gustavo
 */
public class TurmaDbDAO implements TurmaDAO {

	private ArrayList<Turma> turmas;
	private ArrayList<Nota> notas;

	public TurmaDbDAO() {
		turmas = new ArrayList<Turma>();
		notas = new ArrayList<Nota>();
	}

	public ArrayList<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<Turma> turmas) {
		this.turmas = turmas;
	}

	public void addNota(Turma turma, Nota nota) {
		turma.getNotas().add(nota);
	}

	public void addNota(Turma turma, double notaAlu, Aluno aluno) {
		Nota nota = new Nota(notaAlu, aluno);
		turma.getNotas().add(nota);
	}

	public void updateNota(Turma turma, Nota nota) {
		this.excludeNotaTurma(nota.getAluno(), turma);
		this.addNota(turma, nota);
	}

	public void excludeNota(Nota nota, Turma turma) {
		this.excludeNotaTurma(nota.getAluno(), turma);
	}

	public String[] getInfoNotas(ArrayList notas) {
		String[] info = new String[notas.size()];
		for (int i = 0; i < notas.size(); i++) {
			Nota nota = (Nota) notas.get(i);
			info[i] = nota.getAluno().getIdPessoa() + " - "
					+ nota.getAluno().getRA() + " - "
					+ nota.getAluno().getNomePessoa() + " - " + nota.getNota();
		}
		return info;
	}

	public Nota findNota(Turma turma, Aluno aluno) {
		Nota notaLaco, notaRetorno;
		notaRetorno = null;
		Iterator<Nota> iteratorNotas = turma.getNotas().iterator();
		while (iteratorNotas.hasNext()) {
			notaLaco = iteratorNotas.next();
			Aluno alunoLaco = (Aluno) notaLaco.getAluno();
			if (alunoLaco.getRA().equals(aluno.getRA())) {
				notaRetorno = notaLaco;
				break;
			}
		}
		return notaRetorno;
	}

	public void excludeNotaTurma(Aluno aluno, Turma turma) {
		Nota nota = this.findNota(turma, aluno);
		if (nota != null)
			turma.getNotas().remove(nota);
	}
}