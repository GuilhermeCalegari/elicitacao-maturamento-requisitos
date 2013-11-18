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
	/** Login do usuário (campo da tela). */
	private String login;
	
	/** Senha do usuário (campo da tela). */
	private String senha;
	
	/** Usuário da sessão. */
	private Usuario usuario;
	
	/** Mensagem da tela de login. */
	private String mensagemTela = "Bem-Vindo ao GISCover Fibra";
	
	/** Falg que indica perfil de administrador. */
	private boolean perfilAdministrador;
	
	/** String que será exibida no topo da página, informando o nome do usuário e o seu perfil.
	 *  Caso o usuário não esteja logado o valor da variável será: Usuário não logado.
	 */
	private String usuarioLogado;
	
	//Constantes
	private final String tituloMsgLogin = "Login";
	
	/**
	 * Contrutor padrão que inicializa alguns componentes.
	 */
	public LoginMB() 
	{
		this.usuarioLogado = null;
		this.perfilAdministrador = false;
	}//fim construtor
	
	/**
	 * Método responsável por autenticar um usuário.
	 * @param usuario .
	 * @return .
	 */
	public boolean autenticarUsuario(Usuario usuario)
	{
		System.out.println("Autenticando usuário...");
		return true;	
	}//fim autenticarUsuário
	
	/**
	 * Método responsável por autenticar um usuário na aplicação Giscover Fibra.
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