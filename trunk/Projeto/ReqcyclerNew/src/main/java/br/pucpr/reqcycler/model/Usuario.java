package br.pucpr.reqcycler.model;

import java.util.List;

/**
 * Bean que simboliza um usuário do GiscoverFibra.
 * @author G0019778 - Luiz Bernardo Martins Kummer
 *
 */
public class Usuario
{
	/** Login do usuário (Ex: g0019778). */
	private String login;

	/** Lista de perfis que o usuário possui. */
	private List<String> perfis;

	
	//Getters e Setters
	/**
	 * @return the login
	 */
	public String getLogin() 
	{
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) 
	{
		this.login = login;
	}

	/**
	 * @return the perfis
	 */
	public List<String> getPerfis() 
	{
		return perfis;
	}

	/**
	 * @param perfis the perfis to set
	 */
	public void setPerfis(List<String> perfis) 
	{
		this.perfis = perfis;
	}
	
} //fim classe Usuario