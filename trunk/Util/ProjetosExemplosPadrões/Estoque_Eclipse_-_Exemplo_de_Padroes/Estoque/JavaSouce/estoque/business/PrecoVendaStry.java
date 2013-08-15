package estoque.business;

import java.math.BigDecimal;

import estoque.util.GlobalParameter;


// Padr�o Strategy: ConcreteStrategy
public class PrecoVendaStry implements PoliticaPrecoStry {
	public BigDecimal algoritmoPreco(BigDecimal preco) {
		BigDecimal precoVenda;
		// Aumenta em 10% o Pre�o de Custo
		precoVenda = preco.multiply(new BigDecimal(1.1));
		// Multiplica o Pre�o pelo Markup Padr�o
		precoVenda = precoVenda.multiply(GlobalParameter.getInstance().getMarkUp());
		// Arredonda os resultados em duas casas decimais
		precoVenda = precoVenda.setScale(2, BigDecimal.ROUND_DOWN);
		return precoVenda;
	}
}
