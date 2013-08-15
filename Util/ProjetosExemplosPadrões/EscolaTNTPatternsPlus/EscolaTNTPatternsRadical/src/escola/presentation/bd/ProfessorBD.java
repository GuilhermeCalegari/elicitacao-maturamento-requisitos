package escola.presentation.bd;

import escola.business.bo.ProfessorBO;
import escola.business.to.Professor;

/**
 * 
 * @author Ricardo Azevedo 
 * Implementa o padrao de projeto Business Delegate
 */
public class ProfessorBD {

	// Busines Object
	private ProfessorBO professorBO;

	// Transfer Object
	private Professor professor;

	public ProfessorBD() {
		professorBO = new ProfessorBO();
	}

	// ////////////////////////////////////
	// METODOS PARA TRATAR PROFESSORES //
	// ////////////////////////////////////
	public void addProfessor(String pIdPessoa, String pEspecialidade,
			String pNomePessoa) {
		carregaDados(pIdPessoa, pEspecialidade, pNomePessoa);
		professorBO.addProfessor(professor);
	}

	public void updateProfessor(String pIdPessoa, String pEspecialidade,
			String pNomePessoa) {
		carregaDados(pIdPessoa, pEspecialidade, pNomePessoa);
		professorBO.updateProfessor(professor);
	}

	public String[] updateListProfessor() {
		return professorBO.updateListProfessor();
	}

	public void excludeProfessor(String idProfessor) {
		professorBO.excludeProfessor(idProfessor);
	}

	public String consultProfessor(String nomeProfessor) {
		return professorBO.consultProfessor(nomeProfessor);
	}

	public String[] getInfoProfessores() {
		return professorBO.getInfoProfessores();
	}

	public void carregaDados(String pIdPessoa, String pEspecialidade,
			String pNomePessoa) {
		/*
		 * Instancia um Transfer Object para armazenar as informacoes e
		 * tranferi-las para as demais camadas
		 */
		professor = new Professor();
		professor.setIdPessoa(Integer.parseInt(pIdPessoa));
		professor.setEspecialidade(pEspecialidade);
		professor.setNomePessoa(pNomePessoa);

	}

}
