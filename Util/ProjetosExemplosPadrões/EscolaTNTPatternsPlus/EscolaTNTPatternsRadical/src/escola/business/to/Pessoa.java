package escola.business.to;

public class Pessoa {

	private int idPessoa;
	private String nomePessoa;

	public Pessoa() {
		nomePessoa = new String();
	}

	public Pessoa(int id, String nome) {
		setIdPessoa(id);
		setNomePessoa(nome);
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public void setNomePessoa(String nome) {
		nomePessoa = nome;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

}