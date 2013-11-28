package br.pucpr.reqcycler.enumeration;

import java.io.Serializable;

public enum ClassificacaoRequisitoEnum implements Serializable {
	BAIXO("Baixo"), MEDIO("Medio"), ALTO("Alto");

	private final String classificacao;

	private ClassificacaoRequisitoEnum(String classificacao) {
		this.classificacao = classificacao;
	}

	/**
	 * @return the classificacaoRequisitoEnum
	 */
	public String getClassificacao() {
		return classificacao;
	}
	

}
