package br.pucpr.reqcycler.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.DragDropEvent;

import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.model.Usuario;
import br.pucpr.reqcycler.service.impl.ProjetoService;
import br.pucpr.reqcycler.service.impl.UsuarioService;

@ManagedBean(name="projetoRecursosController")
@SessionScoped
public class ProjetoRecursosController implements Serializable {
	private static final long serialVersionUID = 1L;

    private List<SelectItem> comboProjetos;
    private List<SelectItem> comboUsuarios;
	private List<Usuario> usuariosDisponiveis;
	private List<Usuario> usuariosSelecionados;
	private List<Projeto> projetosDisponiveis;
	private Projeto projetoSelecionado;
	private Usuario usuarioSelecionado;

	@ManagedProperty(value = "#{projetoService}")
	private ProjetoService projetoService;
	
	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;
	

	public ProjetoRecursosController() {
		usuariosDisponiveis = new ArrayList<Usuario>();
		usuariosSelecionados = new ArrayList<Usuario>();
		projetoSelecionado = new Projeto();
		usuarioSelecionado = new Usuario();

		Usuario u1 = new Usuario();
		u1.setLogin("lucasdoliveira");
		u1.setEmail("lucasdoliveira@ici.curitiba.org.br");
		u1.setNome("Lucas");
		u1.setSobrenome("Oliveira");
		usuariosDisponiveis.add(u1);

		Usuario u2 = new Usuario();
		u2.setLogin("ronaldoFenomeno");
		u2.setEmail("ronaldoFenomeno@ici.curitiba.org.br");
		u2.setNome("Ronaldo");
		u2.setSobrenome("Fenomeno");
		usuariosDisponiveis.add(u2);
	}

	@PostConstruct
	private void init() {
	}
	
	public void popularComboProjetos() {
		try {
            comboProjetos = new ArrayList<SelectItem>();
			List<Projeto> projetos = projetoService.getProjetos();

            for (Projeto projeto : projetos) {
                SelectItem projetoSelect = new SelectItem(projeto.getId(), projeto.getNome());
                comboProjetos.add(projetoSelect);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public List<Usuario> getUsuariosDisponiveis() {
		return usuariosDisponiveis;
	}

	public void setUsuariosDisponiveis(List<Usuario> usuariosDisponiveis) {
		this.usuariosDisponiveis = usuariosDisponiveis;
	}

	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}

	public void onRecursoSelecionado(DragDropEvent ddEvent) {
		Usuario usuario = ((Usuario) ddEvent.getData());
		usuariosDisponiveis.remove(usuario);
		usuariosSelecionados.add(usuario);
	}

	public List<Projeto> getProjetosDisponiveis() {
		return projetosDisponiveis;
	}

	public void setProjetosDisponiveis(List<Projeto> projetosDisponiveis) {
		this.projetosDisponiveis = projetosDisponiveis;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

	public List<SelectItem> getComboProjetos() {
		try {
            comboProjetos = new ArrayList<SelectItem>();
			List<Projeto> projetos = projetoService.getProjetos();

            for (Projeto projeto : projetos) {
                SelectItem projetoSelect = new SelectItem(projeto.getId(), projeto.getNome());
                comboProjetos.add(projetoSelect);
            }
            return comboProjetos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void verificarObjetos() {
		System.out.println("---------------------------------------------------");
		System.out.println(projetoSelecionado.getId()+", "+projetoSelecionado.getNome());
		System.out.println(usuarioSelecionado.getId()+", "+usuarioSelecionado.getNome());
		System.out.println("---------------------------------------------------");
	}

	public void setComboProjetos(List<SelectItem> comboProjetos) {
		this.comboProjetos = comboProjetos;
	}

	public ProjetoService getProjetoService() {
		return projetoService;
	}

	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}

	public List<SelectItem> getComboUsuarios() {
		try {
            comboUsuarios = new ArrayList<SelectItem>();
			List<Usuario> usuarios = usuarioService.getUsuarios();

            for (Usuario usuario : usuarios) {
                SelectItem usuarioSelect = new SelectItem(usuario.getId(), usuario.getNome());
                comboUsuarios.add(usuarioSelect);
            }
            return comboUsuarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setComboUsuarios(List<SelectItem> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
}
