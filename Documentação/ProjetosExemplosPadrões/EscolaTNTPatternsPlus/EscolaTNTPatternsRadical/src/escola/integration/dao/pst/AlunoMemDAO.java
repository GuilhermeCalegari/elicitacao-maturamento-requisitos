package escola.integration.dao.pst;

import java.util.ArrayList;
import java.util.Iterator;

import escola.business.to.Aluno;
import escola.integration.dao.AlunoDAO;

/**
 * Padrão Abstract Factory (Concrete Product) 
 * 
 * @author Luiz Gustavo
 */
public class AlunoMemDAO implements AlunoDAO {
	
	private ArrayList<Aluno> alunos;

	public AlunoMemDAO() {
		alunos = new ArrayList<Aluno>();
		
		Aluno a1 = new Aluno(10, "DIEGO SOBRAL", "1010");
		Aluno a2 = new Aluno(20, "DANY SOBRAL", "2020");
		Aluno a3 = new Aluno(30, "RICARDO AZEVEDO", "3030");
		
		alunos.add(a1);
		alunos.add(a2);
		alunos.add(a3);
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void addAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}

	public Aluno findAluno(Aluno aluno) {
		Aluno alunoPercorre, alunoRetorno;
		Iterator iteratorAlunos;
		alunoRetorno = null;
		iteratorAlunos = alunos.iterator();
		while (iteratorAlunos.hasNext()) {
			alunoPercorre = (Aluno) iteratorAlunos.next();
			if (alunoPercorre.getRA().equals(aluno.getRA())) {
				alunoRetorno = alunoPercorre;
				break;
			}
		}
		return alunoRetorno;
	}

	public Aluno getAlunoPorName(String nomeAluno) {
		Aluno alunoPercorre, alunoRetorno;

		alunoRetorno = null;
		Iterator iteratorAlunos = alunos.iterator();
		while (iteratorAlunos.hasNext()) {
			alunoPercorre = (Aluno) iteratorAlunos.next();
			if (alunoPercorre.getNomePessoa().equals(nomeAluno))
				alunoRetorno = alunoPercorre;
		}
		return alunoRetorno;
	}

	public void updateAluno(Aluno aluno) {
		Aluno alunoRetorno = findAluno(aluno);
		if (alunoRetorno != null) {
			alunoRetorno.setNomePessoa(aluno.getNomePessoa());
			alunoRetorno.setRA(aluno.getRA());
		}
	}

	public String[] getInfoAlunos() {
		String[] info = new String[alunos.size()];
		for (int i = 0; i < alunos.size(); i++) {
			Aluno aluno = (Aluno) alunos.get(i);
			info[i] = aluno.getIdPessoa() + " - " + aluno.getRA() + " - "
					+ aluno.getNomePessoa();
		}
		return info;
	}

	public void excludeAluno(Aluno aluno) {
		Aluno alu = findAluno(aluno);
		if (alu != null) {
			alunos.remove(alu);
		}
	}
}
