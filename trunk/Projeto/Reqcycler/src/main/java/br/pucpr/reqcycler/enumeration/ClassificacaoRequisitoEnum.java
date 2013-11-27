package br.pucpr.reqcycler.enumeration;

import java.io.Serializable;

public enum ClassificacaoRequisitoEnum implements Serializable {
	BAIXO("Baixo"), MEDIO("Medio"), ALTO("enum_alto");

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
