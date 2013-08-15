package escola.business.to;


public class Aluno extends Pessoa {
	
	private String ra;

	
	public Aluno() {
		ra = new String();
	}

	public Aluno(int idPessoa, String nomePessoa, String num) {
		super(idPessoa, nomePessoa);
		setRA(num);
	}

	public void setRA(String num) {
		ra = num;
	}

	public String getRA() {
		return ra;
	}
}