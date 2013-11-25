package br.pucpr.reqcycler.enumeration;

import java.io.Serializable;

public enum StatusProjetoEnum implements Serializable {
	ABERTO("Aberto"), FECHADO("Fechado");

	private final String status;

	private StatusProjetoEnum(String status) {
		this.status = status;
	}

	/**
	 * @return the classificacaoRequisitoEnum
	 */
	public String getStatus() {
		return status;
	}
	  

}
