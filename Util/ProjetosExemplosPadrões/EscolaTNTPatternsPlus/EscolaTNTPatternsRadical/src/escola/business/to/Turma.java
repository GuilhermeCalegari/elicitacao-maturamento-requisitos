package escola.business.to;

import java.util.ArrayList;

public class Turma {

	private String idTurma;
	private String periodo;
	private Professor professor;
	private ArrayList<Nota> notas = new ArrayList<Nota>();

	public Turma() {
	}

	public Turma(String idTurma, Professor prof, String per) {
		setIdTurma(idTurma);
		setProfessor(prof);
		setPeriodo(per);
		setNotas(new ArrayList<Nota>());
	}

	public void setIdTurma(String id) {
		idTurma = id;
	}

	public String getIdTurma() {
		return idTurma;
	}

	public void setProfessor(Professor prof) {
		professor = prof;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setPeriodo(String per) {
		periodo = per;
	}

	public String getPeriodo() {
		return periodo;
	}

	public ArrayList getNotas() {
		return notas;
	}

	public void setNotas(ArrayList<Nota> notas) {
		this.notas = notas;
	}
}