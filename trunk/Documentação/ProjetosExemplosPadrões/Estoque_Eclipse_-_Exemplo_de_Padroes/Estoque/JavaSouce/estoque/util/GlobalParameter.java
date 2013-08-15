package estoque.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import estoque.business.PoliticaPrecoStry;
import estoque.business.PrecoVendaStry;
import estoque.business.to.Categoria;

// Padrão Singleton : Singleton para atributos globais de uma aplicação
// Padrão Strategy : Realiza também o papel do "Context" do padrão Strategy, 
// que controla a estratégia de preços 
public final class GlobalParameter {
	
	// atributo estático para referência única
	private static GlobalParameter instance = null;
	
	// Atributo para tratamento de log
	public static final Logger logger = Logger.getLogger(GlobalParameter.class);	

	// atributo da classe
	private String usuario;

	private BigDecimal markUp;

	private PoliticaPrecoStry politicaPreco;

	// Lista de Categorias que foram inativadas na sessão.
	// Esta lista é atualizada pela implementação do Padrão Observer
	private ArrayList<Categoria> listaCategoriaInativa;

	// construtor privados
	private GlobalParameter() {
		
		// atributos da classe podem ser normalmente inicializados
		usuario = "admin";
		markUp = new BigDecimal(1);
		politicaPreco = new PrecoVendaStry(); // O preço padrão é o preço de Venda
		listaCategoriaInativa = new ArrayList<Categoria>();
	}

	public static GlobalParameter getInstance() {
		if (instance == null) {
			// instanciação ocorre apenas se um objeto for solicitado
			instance = new GlobalParameter();
		}
		return instance;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getMarkUp() {
		return markUp;
	}

	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}

	public PoliticaPrecoStry getPoliticaPreco() {
		return politicaPreco;
	}

	public void setPoliticaPreco(PoliticaPrecoStry politicaPreco) {
		this.politicaPreco = politicaPreco;
	}

	BigDecimal precoAplicavel(BigDecimal preco) {
		return politicaPreco.algoritmoPreco(preco);
	}

	void trocaAlgoritmo(PoliticaPrecoStry novoAlgoritmo) {
		politicaPreco = novoAlgoritmo;
	}

	public void imprimeValores(GlobalParameter globalParam) {
		System.out.println("Parâmetros Globais---> ");
		System.out.println("Usuário          	-> " + globalParam.usuario);
		System.out.println("markUp de Preços 	-> " + globalParam.markUp);
		System.out.println("Política de Preços 	-> "
				+ globalParam.politicaPreco.getClass().getName());

		// Mostra os objetos do Array de Categoria
		// Recursos disponível a partir do Java 5 para percorrer arrays de objetos
		for (Categoria categoria : listaCategoriaInativa) { 
			System.out.println("Categoria: " + categoria.getId() + " - "
					+ categoria.getCategoria() + " foi inativada nesta sessão pelo usuário "
					+ this.usuario);
		}
	}

	public void adicionaCategoriaInativa(Categoria pCategoria) {
		listaCategoriaInativa.add(pCategoria);
	}

	public void removeCategoriaInativa(Categoria pCategoria) {
		Iterator iteratorListaCategoria;
		iteratorListaCategoria = listaCategoriaInativa.iterator();
		Categoria categoria;
		// Percorre a lista de categorias inativadas
		while (iteratorListaCategoria.hasNext()) {
			categoria = (Categoria) iteratorListaCategoria.next();
			if (categoria.getId().equals(pCategoria.getId())) {
				listaCategoriaInativa.remove(categoria);
				break;
			}	
		}
	}
}
