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

import br.pucpr.reqcycler.enumeration.ClassificacaoRequisitoEnum;
import br.pucpr.reqcycler.enumeration.ComplexidadeRequisitoEnum;
import br.pucpr.reqcycler.enumeration.StatusRequisitoEnum;
import br.pucpr.reqcycler.model.Projeto;
import br.pucpr.reqcycler.model.Requisito;
import br.pucpr.reqcycler.model.Usuario;
import br.pucpr.reqcycler.service.impl.RequisitoService;

/**
 * 
 * Requisito Managed Bean
 * 
 * @author Rodrigo Moreschi Valoski
 *
 */

@ManagedBean(name="requisitoController")
@SessionScoped
public class RequisitoController implements Serializable {
			
	private static final long serialVersionUID = 1674553816474363886L;

	@ManagedProperty(value = "#{requisitoService}")
	private RequisitoService requisitoService;
	
	public void setRequisitoService(RequisitoService requisitoService) {
		this.requisitoService = requisitoService;
	}
	
	@ManagedProperty(value= "#{usuarioController.usuarioLogado}")
	private Usuario usuarioLogado;		
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}	

	private Requisito requisito;
	
	private Projeto projeto;
	
	private Requisito requisitoSelecionado;	
	
	private List<Requisito> requisitoList;
	
	private List<Requisito> requisitoFiltroList;
		
	
	@PostConstruct
	private void init(){
		limparRequisito();
	}
		
	public void adicionaRequisito() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
				
			requisito.setProjeto(this.projeto);
			requisito.setDataCriado(new Date());
			requisito.setDataAlterado(null);														
			requisito.setUsuarioCriacao(usuarioLogado);
			requisito.setUsuarioAlteracao(null);
			requisito.setStatus(StatusRequisitoEnum.ABERTO);			
			requisitoService.adicionaRequisito(requisito);
						
			context.addMessage(null, new FacesMessage("Transação OK!", 
					                                  "REQUISITO CRIADO COM SUCESSO!"));
									
		} catch (Exception e) {	
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transação NÃO OK!", 
					                                  "REQUISITO NÃO FOI CRIADO!"));
		} 				
		
		limparRequisito();

	}
	
	public void atualizarRequisito(RowEditEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try{
					
			Requisito requisito = (Requisito) event.getObject();
			requisitoService.atualizaRequisito(requisito);
			
			context.addMessage(null, new FacesMessage("Transação OK!", 
                    								  "REQUISITO ALTERADO COM SUCESSO!"));
									
		}catch(Exception e){
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transação NÃO OK!", 
													  "REQUISITO NÃO FOI ALTERADO!"));					
		}
		
		limparRequisito();
				
	} 
			
	public void getRequisitos(){	
		this.requisitoList = new ArrayList<Requisito>();
		this.requisitoList.addAll(requisitoService.getRequisitos());		
	}
	
	public void getRequisitosByProjeto(){				
		this.requisitoList = new ArrayList<Requisito>();
		this.requisitoList.addAll(requisitoService.getRequisitosByProjeto(projeto));		
	}
	
	public void limparRequisito() {			
		this.requisito = new Requisito();
		this.projeto = new Projeto();	
		this.requisitoList = new ArrayList<Requisito>();
	}	
	
	public SelectItem[] getComplexidadeValues() {
	    SelectItem[] items = new SelectItem[ComplexidadeRequisitoEnum.values().length];
	    int i = 0;
	    for(ComplexidadeRequisitoEnum g: ComplexidadeRequisitoEnum.values()) {
	      items[i++] = new SelectItem(g, g.getComplexidade());
	    }
	    return items;
	 }
	
	public SelectItem[] getClassificacaoValues() {
	    SelectItem[] items = new SelectItem[ClassificacaoRequisitoEnum.values().length];
	    int i = 0;
	    for(ClassificacaoRequisitoEnum g: ClassificacaoRequisitoEnum.values()) {
	      items[i++] = new SelectItem(g, g.getClassificacao());
	    }
	    return items;
	 }
	
	public SelectItem[] getStatusValues() {
	    SelectItem[] items = new SelectItem[StatusRequisitoEnum.values().length];
	    int i = 0;
	    for(StatusRequisitoEnum g: StatusRequisitoEnum.values()) {
	      items[i++] = new SelectItem(g, g.getStatus());
	    }
	    return items;
	 }
				
	/**
	 * @return the requisito
	 */
	public Requisito getRequisito() {
		return requisito;
	}

	/**
	 * @param requisito the requisito to set
	 */
	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
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
	 * @return the requisitoSelecionado
	 */
	public Requisito getRequisitoSelecionado() {
		return requisitoSelecionado;
	}

	/**
	 * @param requisitoSelecionado the requisitoSelecionado to set
	 */
	public void setRequisitoSelecionado(Requisito requisitoSelecionado) {
		this.requisitoSelecionado = requisitoSelecionado;
	}

	/**
	 * @return the requisitoList
	 */
	public List<Requisito> getRequisitoList() {
		return requisitoList;
	}

	/**
	 * @param requisitoList the requisitoList to set
	 */
	public void setRequisitoList(List<Requisito> requisitoList) {
		this.requisitoList = requisitoList;
	}

	/**
	 * @return the requisitoFiltroList
	 */
	public List<Requisito> getRequisitoFiltroList() {
		return requisitoFiltroList;
	}

	/**
	 * @param requisitoFiltroList the requisitoFiltroList to set
	 */
	public void setRequisitoFiltroList(List<Requisito> requisitoFiltroList) {
		this.requisitoFiltroList = requisitoFiltroList;
	}
			
}