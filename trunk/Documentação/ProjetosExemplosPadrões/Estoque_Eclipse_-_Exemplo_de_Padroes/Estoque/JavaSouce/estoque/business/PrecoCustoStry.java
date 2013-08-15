package estoque.business;

import java.math.BigDecimal;

// Padr�o Strategy: ConcreteStrategy
public class PrecoCustoStry implements PoliticaPrecoStry {
	public BigDecimal algoritmoPreco(BigDecimal preco) {
		BigDecimal precoVenda;
		// Aumenta em 10% o pre�o de compra e n�o aplica o Markup
		precoVenda = preco.multiply(new BigDecimal(1.1));
		// Arredonda os resultados em duas casas decimais
		precoVenda = precoVenda.setScale(2, BigDecimal.ROUND_DOWN);
		return precoVenda;
		
	}
}
