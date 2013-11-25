package br.pucpr.reqcycler.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.pucpr.reqcycler.enumeration.ClassificacaoRequisitoEnum;

/**
 * 
 * Requisito Entity
 * 
 * @author Rodrigo Moreschi Valoski
 * 
 */
@Entity
@Table(name = "REQUISITO")
public class Requisito {

	private int id;
	private Projeto projeto;
	private ClassificacaoRequisitoEnum classificacao;
	private Date dataCriado;
	private String tipo;
	private String complexidade;
	private Date descricao;
	private String status;

	/**
	 * @return the Id
	 */
	@Id
	@SequenceGenerator(name = "REQUISITO_SEQ", sequenceName = "REQUISITO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUISITO_SEQ")
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
	 * @return the projeto
	 */
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Projeto.class)
    @JoinColumn(name = "PROJETO", unique = false, nullable = false)	
	public Projeto getProjeto() {
		return projeto;
	}

	/**
	 * @param projeto
	 *            the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	/**
	 * @return the classificacao
	 */
	@Column(name = "CLASSIFICACAO", unique = false, nullable = true)
	@Enumerated(EnumType.STRING)
	public ClassificacaoRequisitoEnum getClassificacao() {
		return classificacao;
	}

	/**
	 * @param classificacao
	 *            the classificacao to set
	 */
	public void setClassificacao(ClassificacaoRequisitoEnum classificacao) {
		this.classificacao = classificacao;
	}

	/**
	 * @return the dataCriado
	 */
	@Column(name = "DATA_CRIADO", unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getDataCriado() {
		return dataCriado;
	}

	/**
	 * @param dataCriado
	 *            the dataCriado to set
	 */
	public void setDataCriado(Date dataCriado) {
		this.dataCriado = dataCriado;
	}

	/**
	 * @return the tipo
	 */
	@Column(name = "TIPO", unique = false, nullable = true)
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the complexidade
	 */
	@Column(name = "COMPLEXIDADE", unique = false, nullable = true)
	public String getComplexidade() {
		return complexidade;
	}

	/**
	 * @param complexidade
	 *            the complexidade to set
	 */
	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}

	/**
	 * @return the descricao
	 */

	@Column(name = "DESCRICAO", unique = false, nullable = false)
	public Date getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(Date descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the status
	 */
	@Column(name = "STATUS", unique = false, nullable = false)
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
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
