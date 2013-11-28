package br.pucpr.reqcycler.enumeration;

import java.io.Serializable;

public enum ComplexidadeRequisitoEnum implements Serializable {
	BAIXO("Baixo"), MEDIO("Medio"), ALTO("Alto");

	private final String complexidade;

	private ComplexidadeRequisitoEnum(String complexidade) {
		this.complexidade = complexidade;
	}

	/**
	 * @return the complexidadeRequisitoEnum
	 */
	public String getComplexidade() {
		return complexidade;
	}
	

}
