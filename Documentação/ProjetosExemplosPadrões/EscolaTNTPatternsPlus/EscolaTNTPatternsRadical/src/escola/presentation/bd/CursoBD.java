package escola.presentation.bd;

import escola.business.bo.CursoBO;
import escola.business.to.Curso;

/**
 * @author Ricardo Azevedo
 * Implementa o padrao de projeto Business Delegate
 */
public class CursoBD {

	// Business Object
	private CursoBO cursoBO;

	// Transfer Object
	private Curso curso;

	public CursoBD() {
		cursoBO = new CursoBO();
	}

	// /////////////////////////////
	// METODOS PARA TRATAR CURSO //
	// /////////////////////////////

	public void addCurso(String pIdCurso, String pNomeCurso) {
		carregaDados(pIdCurso, pNomeCurso);
		cursoBO.addCurso(curso);
	}

	public void updateCurso(String pIdCurso, String pNomeCurso) {
		carregaDados(pIdCurso, pNomeCurso);
		cursoBO.updateCurso(curso);
	}

	public void excludeCurso(String pIdCurso) {
		cursoBO.excludeCurso(pIdCurso);
	}

	public String[] getInfoCursos() {
		return cursoBO.getInfoCursos();
	}

	public void carregaDados(String pIdCurso, String pNomeCurso) {
		/*
		 * Instancia um Transfer Object para armazenar as informacoes e
		 * tranferi-las para as demais camadas
		 */
		curso = new Curso();
		curso.setIdCurso(pIdCurso);
		curso.setNomeCurso(pNomeCurso);
	}

}
