package escola.business.bo;

import escola.business.to.Curso;
import escola.integration.dao.EscolaPST;

/**
 * Implementa o padrao de projeto Business Object
 * 
 */
public class CursoBO {

	private EscolaPST fachadaPersistencia;

	private EscolaPST getFachadaPersistencia() {
		if (fachadaPersistencia == null) {
			fachadaPersistencia = EscolaPST.getInstance();
		}
		return fachadaPersistencia;
	}

	public CursoBO() {
	}

	// /////////////////////////////
	// METODOS PARA TRATAR CURSO //
	// /////////////////////////////

	public void addCurso(Curso curso) {
		getFachadaPersistencia().addCurso(curso);
	}

	public void updateCurso(Curso curso) {
		getFachadaPersistencia().updateCurso(curso);
	}

	public void excludeCurso(String idCurso) {
		Curso curso = new Curso();
		curso.setIdCurso(idCurso);
		getFachadaPersistencia().excludeCurso(curso);
	}

	public String[] getInfoCursos() {
		return getFachadaPersistencia().getInfoCursos();
	}
}
