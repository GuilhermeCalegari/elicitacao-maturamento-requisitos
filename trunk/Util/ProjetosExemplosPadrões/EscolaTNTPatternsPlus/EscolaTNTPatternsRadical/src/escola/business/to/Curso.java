package escola.business.to;

import java.util.ArrayList;

public class Curso {

	private String idCurso;
	private String nomeCurso;
	private ArrayList<Turma> turmas = new ArrayList<Turma>();

	public Curso() {
		turmas = new ArrayList<Turma>();
	}

	public Curso(String idCurso, String nome) {
		this();
		setIdCurso(idCurso);
		setNome(nome);
	}

	public void setNome(String nome) {
		this.nomeCurso = nome;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public ArrayList<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<Turma> turmas) {
		this.turmas = turmas;
	}

	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
}