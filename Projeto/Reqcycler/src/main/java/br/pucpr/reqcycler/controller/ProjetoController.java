package br.pucpr.reqcycler.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import br.pucpr.reqcycler.enumeration.StatusProjetoEnum;
import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.model.Usuario;
import br.pucpr.reqcycler.service.impl.ProjetoService;

/**
 * 
 * Projeto Managed Bean
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="projetoController")
@SessionScoped
public class ProjetoController implements Serializable {
			
	private static final long serialVersionUID = 1674553816474363886L;

	@ManagedProperty(value = "#{projetoService}")
	private ProjetoService projetoService;
	
	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}
	
	@ManagedProperty(value= "#{usuarioController.usuarioLogado}")
	private Usuario usuarioLogado;		
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	private Projeto projeto;
	
	private Projeto projetoSelecionado;
	
	private List<Projeto> projetoList;
	
	private List<Projeto> projetoFiltroList;
	
	@PostConstruct
	private void init(){
		limparProjeto();
	}
		
	public void adicionaProjeto() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			
			projeto.setSponsor(this.usuarioLogado);
			projeto.setDataInicio(new Date());								
			projeto.setStatus(StatusProjetoEnum.ABERTO);			
			projetoService.adicionaProjeto(projeto);
						
			context.addMessage(null, new FacesMessage("Transa��o OK!", 
					                                  "PROJETO CRIADO COM SUCESSO!"));
									
		} catch (Exception e) {	
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transa��o N�O OK!", 
					                                  "PROJETO N�O FOI CRIADO!"));
		} 				
		
		limparProjeto();

	}
	
	public void atualizarProjeto(RowEditEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try{
					
			Projeto projeto = (Projeto) event.getObject();
			
			if ("FECHADO".equalsIgnoreCase(projeto.getStatus().getStatus())){
				projeto.setDataFim(new Date());
			}else{
				projeto.setDataFim(null);
			}
			
			projetoService.atualizaProjeto(projeto);
			
			context.addMessage(null, new FacesMessage("Transa��o OK!", 
                    								  "PROJETO ALTERADO COM SUCESSO!"));
									
		}catch(Exception e){
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transa��o N�O OK!", 
													  "PROJETO N�O FOI ALTERADO!"));					
		}
		
		limparProjeto();
				
	} 
			
	public void limparProjeto() {		
		this.projeto = new Projeto();		
		this.projetoList = new ArrayList<Projeto>();
		this.projetoList.addAll(getProjetos());
	}	
	
	public List<Projeto> getProjetos(){
		return projetoService.getProjetos();		
	}
	
	public SelectItem[] getProjetosValues() {
		List<Projeto> listProjetos = this.getProjetos();
	    SelectItem[] items = new SelectItem[listProjetos.size()];
	    int i = 0;
	    for(Projeto projeto: listProjetos) {
	      items[i++] = new SelectItem(projeto.getId(), projeto.getNome());
	    }
	    return items;
	}
	
	public SelectItem[] getStatusValues() {
	    SelectItem[] items = new SelectItem[StatusProjetoEnum.values().length];
	    int i = 0;
	    for(StatusProjetoEnum g: StatusProjetoEnum.values()) {
	      items[i++] = new SelectItem(g, g.getStatus());
	    }
	    return items;
	 }
				
	/**
	 * @return the projeto
	 */
	public Projeto getProjeto() {
		return projeto;
	}

	/**
	 * @param projeto the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	/**
	 * @return the projetoSelecionado
	 */
	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	/**
	 * @param projetoSelecionado the projetoSelecionado to set
	 */
	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

	/**
	 * @return the projetoList
	 */
	public List<Projeto> getProjetoList() {
		return projetoList;
	}

	/**
	 * @param projetoList the projetoList to set
	 */
	public void setProjetoList(List<Projeto> projetoList) {
		this.projetoList = projetoList;
	}

	/**
	 * @return the projetoFiltroList
	 */
	public List<Projeto> getProjetoFiltroList() {
		return projetoFiltroList;
	}

	/**
	 * @param projetoFiltroList the projetoFiltroList to set
	 */
	public void setProjetoFiltroList(List<Projeto> projetoFiltroList) {
		this.projetoFiltroList = projetoFiltroList;
	}
			
}