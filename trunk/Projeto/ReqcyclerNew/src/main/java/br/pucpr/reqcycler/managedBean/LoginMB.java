package br.pucpr.reqcycler.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.pucpr.reqcycler.model.Usuario;

@SessionScoped
@ManagedBean
/**
 * Managed Bean da tela de Login.
 * @author G0019778 - Luiz Bernardo Martins Kummer
 *
 */
public class LoginMB
{
	/** Login do usu�rio (campo da tela). */
	private String login;
	
	/** Senha do usu�rio (campo da tela). */
	private String senha;
	
	/** Usu�rio da sess�o. */
	private Usuario usuario;
	
	/** Mensagem da tela de login. */
	private String mensagemTela = "Bem-Vindo ao GISCover Fibra";
	
	/** Falg que indica perfil de administrador. */
	private boolean perfilAdministrador;
	
	/** String que ser� exibida no topo da p�gina, informando o nome do usu�rio e o seu perfil.
	 *  Caso o usu�rio n�o esteja logado o valor da vari�vel ser�: Usu�rio n�o logado.
	 */
	private String usuarioLogado;
	
	//Constantes
	private final String tituloMsgLogin = "Login";
	
	/**
	 * Contrutor padr�o que inicializa alguns componentes.
	 */
	public LoginMB() 
	{
		this.usuarioLogado = null;
		this.perfilAdministrador = false;
	}//fim construtor
	
	/**
	 * M�todo respons�vel por autenticar um usu�rio.
	 * @param usuario .
	 * @return .
	 */
	public boolean autenticarUsuario(Usuario usuario)
	{
		System.out.println("Autenticando usu�rio...");
		return true;	
	}//fim autenticarUsu�rio
	
	/**
	 * M�todo respons�vel por autenticar um usu�rio na aplica��o Giscover Fibra.
	 */
	public void buscarUsuarioNoLDap()
	{
		Usuario usuario = new Usuario();
		
		if(!autenticarUsuario(usuario)){
			usuario = null;
		}else{
			System.out.println("Logado");			
		}	
		
	}
	
	//Getters e Setters
	
	/**
	 * @return .
	 */
	public String getLogin() 
	{
		return login;
	}

	/**
	 * @param login .
	 */
	public void setLogin(String login) 
	{
		this.login = login;
	}

	/**
	 * @return .
	 */
	public String getSenha() 
	{
		return senha;
	}

	/**
	 * @param senha .
	 */
	public void setSenha(String senha) 
	{
		this.senha = senha;
	}

	/**
	 * @return .
	 */
	public Usuario getUsuario() 
	{
		return usuario;
	}

	/**
	 * @param usuario .
	 */
	public void setUsuario(Usuario usuario) 
	{
		this.usuario = usuario;
	}

	/**
	 * @return .
	 */
	public String getUsuarioLogado() 
	{
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado .
	 */
	public void setUsuarioLogado(String usuarioLogado) 
	{
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * @return .
	 */
	public String getMensagemTela() 
	{
		return mensagemTela;
	}

	/**
	 * @param mensagemTela .
	 */
	public void setMensagemTela(String mensagemTela) 
	{
		this.mensagemTela = mensagemTela;
	}

	/**
	 * @return .
	 */
	public boolean isPerfilAdministrador() 
	{
		return perfilAdministrador;
	}

	/**
	 * @param perfilAdministrador .
	 */
	public void setPerfilAdministrador(boolean perfilAdministrador) 
	{
		this.perfilAdministrador = perfilAdministrador;
	}
	
} //fim classe Login MB