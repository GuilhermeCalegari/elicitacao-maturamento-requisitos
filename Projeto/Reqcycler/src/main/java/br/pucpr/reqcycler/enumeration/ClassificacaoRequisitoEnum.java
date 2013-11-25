package br.pucpr.reqcycler.enumeration;

import java.io.Serializable;

public enum ClassificacaoRequisitoEnum implements Serializable {
	BAIXO("enum_baixo"), MEDIO("enum_medio"), ALTO("enum_alto");

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
