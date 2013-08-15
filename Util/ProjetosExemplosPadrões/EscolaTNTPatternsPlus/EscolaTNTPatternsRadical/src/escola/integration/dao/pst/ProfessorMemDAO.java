package escola.integration.dao.pst;

import java.util.ArrayList;
import java.util.Iterator;

import escola.business.to.Professor;
import escola.integration.dao.ProfessorDAO;

/**
 * Padrão Abstract Factory (Concrete Product) 
 * 
 * @author Luiz Gustavo
 */
public class ProfessorMemDAO implements ProfessorDAO {
	
	private ArrayList<Professor> professores;

	public ProfessorMemDAO() {
		professores = new ArrayList<Professor>();
		
		Professor p1 = new Professor(1, "DIOGO", "JAVA");
		Professor p2 = new Professor(2, "FREDY", "BANCO");
		Professor p3 = new Professor(3, "FERNANDO", "METODOLOGIA");
		Professor p4 = new Professor(4, "MARIS", "ENG. SW");
		Professor p5 = new Professor(5, "RICARDO", "PADROES");
		Professor p6 = new Professor(5, "GUSTAVO", "PADROES");
		
		professores.add(p1);
		professores.add(p2);
		professores.add(p3);
		professores.add(p4);
		professores.add(p5);
		professores.add(p6);
	}

	public void addProfessor(Professor professor) {
		this.professores.add(professor);
	}

	public void updateProfessor(Professor prof) {
		Professor professorProcura;
		professorProcura = findProfessor(prof);
		if (professorProcura != null) {
			professorProcura.setEspecialidade(prof.getEspecialidade());
			professorProcura.setNomePessoa(prof.getNomePessoa());
		}
	}

	public void excludeProfessor(Professor prof) {
		professores.remove(prof);
	}

	public Professor findProfessor(Professor prof) {
		Professor professorPercorre, professorRetorno;
		Iterator iteratorProfessores;

		professorRetorno = null;
		iteratorProfessores = professores.iterator();
		while (iteratorProfessores.hasNext()) {
			professorPercorre = (Professor) iteratorProfessores.next();
			if (professorPercorre.getIdPessoa() == prof.getIdPessoa())
				professorRetorno = professorPercorre;
		}
		return professorRetorno;
	}

	public String[] getInfoProfessores() {
		String[] nomes = new String[professores.size()];
		Iterator itProf = professores.iterator();
		int i = 0;
		while (itProf.hasNext()) {
			Professor prof = (Professor) itProf.next();
			nomes[i] = prof.getIdPessoa() + " - " + prof.getNomePessoa().trim()
					+ " - " + prof.getEspecialidade();
			i++;
		}
		return nomes;
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}

	public ArrayList<Professor> getProfessores() {
		return professores;
	}

}
