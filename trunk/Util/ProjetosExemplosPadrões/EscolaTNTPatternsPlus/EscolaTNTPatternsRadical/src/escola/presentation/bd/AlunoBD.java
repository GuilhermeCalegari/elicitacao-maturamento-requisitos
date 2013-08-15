package escola.presentation.bd;

import escola.business.bo.AlunoBO;
import escola.business.to.Aluno;

/**
 * @author Ricardo Azevedo
 * Implementa o padrao de projeto Business Delegate
 * 
 */
public class AlunoBD {

	// Business Object
	private AlunoBO alunoBO;
	// Transfer Object
	private Aluno aluno;

	public AlunoBD() {
		alunoBO = new AlunoBO();
	}

	// /////////////////////////////
	// METODOS PARA TRATAR ALUNO //
	// /////////////////////////////

	public void addAluno(String pIdPessoa, String pNomePessoa, String pRA) {
		carregaAluno(pIdPessoa, pNomePessoa, pRA);

		alunoBO.addAluno(aluno);
	}

	public void updateAluno(String pIdPessoa, String pNomePessoa, String pRA) {
		carregaAluno(pIdPessoa, pNomePessoa, pRA);
		alunoBO.updateAluno(aluno);
	}

	public void excludeAluno(String ra) {
		alunoBO.excludeAluno(ra);
	}

	public String[] getInfoAlunos() {
		return alunoBO.getInfoAlunos();
	}

	private void carregaAluno(String pIdPessoa, String pNomePessoa, String pRA)
			throws NumberFormatException {
		aluno = new Aluno();
		aluno.setIdPessoa(Integer.parseInt(pIdPessoa));
		aluno.setNomePessoa(pNomePessoa);
		aluno.setRA(pRA);
	}
}
