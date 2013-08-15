package escola.business.to;


public class Professor extends Pessoa {
	
	private String especialidade;
	
	public Professor() {
		especialidade = new String();
	}

	public Professor(int idPessoa, String nomePessoa, String espec) {
		super(idPessoa, nomePessoa);
		setEspecialidade(espec);
	}

	public void setEspecialidade(String espec) {
		especialidade = espec;
	}

	public String getEspecialidade() {
		return especialidade;
	}
}