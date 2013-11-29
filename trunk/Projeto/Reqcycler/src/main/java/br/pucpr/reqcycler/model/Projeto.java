package br.pucpr.reqcycler.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.pucpr.reqcycler.enumeration.StatusProjetoEnum;

/**
 * 
 * Projeto Entity
 * 
 * @author Rodrigo Moreschi Valoski
 * 
 */
@Entity
@Table(name = "PROJETO")
public class Projeto {

	private int id;
	private Usuario sponsor;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private String objetivo;
	private String escopo;
	private String descricao;
	private StatusProjetoEnum status;

	/**
	 * @return the Id
	 */
	@Id
	@SequenceGenerator(name = "PROJETO_SEQ", sequenceName = "PROJETO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJETO_SEQ")
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	@Column(name = "NOME", unique = true, nullable = false)
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the sponsor
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "SPONSOR", unique = false, 
				nullable = false, referencedColumnName="ID")	
	public Usuario getSponsor() {
		return sponsor;
	}

	/**
	 * @param sponsor
	 *            the sponsor to set
	 */
	public void setSponsor(Usuario sponsor) {
		this.sponsor = sponsor;
	}

	/**
	 * @return the dataInicio
	 */
	@Column(name = "DATA_INICIO", unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio
	 *            the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	@Column(name = "DATA_FIM", unique = false, nullable = true)
	@Temporal(TemporalType.DATE)
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim
	 *            the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the objetivo
	 */
	@Column(name = "OBJETIVO", unique = false, nullable = true)
	public String getObjetivo() {
		return objetivo;
	}

	/**
	 * @param objetivo
	 *            the objetivo to set
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	/**
	 * @return the escopo
	 */
	@Column(name = "ESCOPO", unique = false, nullable = true)
	public String getEscopo() {
		return escopo;
	}

	/**
	 * @param escopo
	 *            the escopo to set
	 */
	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	/**
	 * @return the descricao
	 */
	@Column(name = "DESCRICAO", unique = false, nullable = true)
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the status
	 */
	@Column(name = "STATUS", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	public StatusProjetoEnum getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusProjetoEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id : ").append(getId());
		return strBuff.toString();
	}
}
