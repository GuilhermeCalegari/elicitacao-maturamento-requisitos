package br.pucpr.reqcycler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * Requisito Entity
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
@Entity
@Table(name="REQUISITO")
public class Requisito {

	private int id;
	private Date   dataCriado;
	private Date   descricao;
	private String status;
	
	/**
	 * @return the Id
	 */
	@Id
	@SequenceGenerator( name = "REQUISITO_SEQ", sequenceName = "REQUISITO_SEQ", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "REQUISITO_SEQ" )  
	@Column(name="ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	/** 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
		
	/**
	 * @return the dataCriado
	 */
	@Column(name="DATA_CRIADO", unique = false, nullable = false)
	public Date getDataCriado() {
		return dataCriado;
	}

	/**
	 * @param dataCriado the dataCriado to set
	 */
	public void setDataCriado(Date dataCriado) {
		this.dataCriado = dataCriado;
	}

	/**
	 * @return the descricao
	 */
	
	@Column(name="DESCRICAO", unique = false, nullable = false)
	public Date getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(Date descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the status
	 */
	@Column(name="STATUS", unique = false, nullable = false)
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id : ").append(getId());			
		return strBuff.toString();
	}
}
