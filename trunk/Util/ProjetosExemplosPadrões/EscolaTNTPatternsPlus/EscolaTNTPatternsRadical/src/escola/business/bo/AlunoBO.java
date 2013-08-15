package escola.business.bo;

import escola.business.to.Aluno;
import escola.integration.dao.EscolaPST;

/**
 *  Implementa o padrao de projeto Business Object
 *
 */
public class AlunoBO {

	private EscolaPST fachadaPersistencia;
	
	private EscolaPST getFachadaPersistencia() {
		if (fachadaPersistencia == null) {
			fachadaPersistencia = EscolaPST.getInstance();
		}
		return fachadaPersistencia;
	}
	
	public AlunoBO() {
	}

	// /////////////////////////////
	// METODOS PARA TRATAR ALUNO //
	// /////////////////////////////

	public void addAluno(Aluno aluno) {
		getFachadaPersistencia().addAluno(aluno);
	}

	public void updateAluno(Aluno aluno) {
		getFachadaPersistencia().updateAluno(aluno);
	}

	public void excludeAluno(String ra) {
		Aluno aluno = new Aluno();
		aluno.setRA(ra);
		getFachadaPersistencia().excludeAluno(aluno);
	}

	public String[] getInfoAlunos() {
		return getFachadaPersistencia().getInfoAlunos();
	}
}
