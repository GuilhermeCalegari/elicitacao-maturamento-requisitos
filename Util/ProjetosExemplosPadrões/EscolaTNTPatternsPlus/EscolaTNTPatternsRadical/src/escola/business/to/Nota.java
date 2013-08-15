package escola.business.to;

public class Nota {
	
	private Aluno aluno;
	private double nota;

	
	public Nota() {
	}

	public Nota(double notaAluno, Aluno aluno) {
		setAluno(aluno);
		nota = notaAluno;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public double getNota() {
		return this.nota;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aluno getAluno() {
		return aluno;
	}
}
