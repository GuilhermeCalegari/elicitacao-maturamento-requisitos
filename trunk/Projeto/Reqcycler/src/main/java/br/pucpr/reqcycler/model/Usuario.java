package br.pucpr.reqcycler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Usuario Entity
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */
@Entity
@Table(name="USUARIO")
public class Usuario {

	private int id;
	private String nome;
	private String sobrenome;
	private Date   dataNascimento;
	private Date   dataInicio;
	private Date   dataFim;
	private String login;
	private String senha;
	
	/**
	 * @return the Id
	 */
	@Id
	@SequenceGenerator( name = "USUARIO_SEQ", sequenceName = "USUARIO_SEQ", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ" )  
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
	 * @return the nome
	 */
	@Column(name="NOME", unique = false, nullable = false)
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the sobreNome
	 */
	@Column(name="SOBRENOME", unique = false, nullable = true)
	public String getSobrenome() {
		return sobrenome;
	}

	/**
	 * @param sobreNome the sobreNome to set
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	/**
	 * @return the dataNascimento
	 */
	
	@Column(name="DATA_NASCIMENTO", unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the dataInicio
	 */
	@Column(name="DATA_INICIO", unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	@Column(name="DATA_FIM", unique = false, nullable = true)
	@Temporal(TemporalType.DATE)
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the senha
	 */
	@Column(name="SENHA", unique = false, nullable = false)
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * @return the login
	 */
	@Column(name="LOGIN", unique = true, nullable = false)
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */	
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id : ").append(getId());			
		return strBuff.toString();
	}
}
