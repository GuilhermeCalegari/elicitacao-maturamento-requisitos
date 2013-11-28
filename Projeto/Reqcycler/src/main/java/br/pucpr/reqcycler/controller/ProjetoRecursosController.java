package br.pucpr.reqcycler.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.DragDropEvent;
import br.pucpr.reqcycler.model.Usuario;

@ManagedBean(name = "projetoRecursosController")
@SessionScoped
public class ProjetoRecursosController implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Usuario> usuariosDisponiveis;
	private List<Usuario> usuariosSelecionados;

	public ProjetoRecursosController() {
		usuariosDisponiveis = new ArrayList<Usuario>();
		usuariosSelecionados = new ArrayList<Usuario>();

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
}
