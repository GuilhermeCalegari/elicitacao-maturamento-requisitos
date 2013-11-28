package br.pucpr.reqcycler.enumeration;

import java.io.Serializable;

public enum StatusRequisitoEnum implements Serializable {	
	ABERTO("Aberto"), PENDENTE("Pendente"), RECUSADO("Recusado"), APROVADO("Recusado");
	private final String status;

	private StatusRequisitoEnum(String status) {
		this.status = status;
	}

	/**
	 * @return the classificacaoRequisitoEnum
	 */
	public String getStatus() {
		return status;
	}
	  

}
