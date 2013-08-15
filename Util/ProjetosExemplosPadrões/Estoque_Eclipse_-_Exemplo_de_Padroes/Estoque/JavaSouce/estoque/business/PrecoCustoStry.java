package estoque.business;

import java.math.BigDecimal;

// Padrão Strategy: ConcreteStrategy
public class PrecoCustoStry implements PoliticaPrecoStry {
	public BigDecimal algoritmoPreco(BigDecimal preco) {
		BigDecimal precoVenda;
		// Aumenta em 10% o preço de compra e não aplica o Markup
		precoVenda = preco.multiply(new BigDecimal(1.1));
		// Arredonda os resultados em duas casas decimais
		precoVenda = precoVenda.setScale(2, BigDecimal.ROUND_DOWN);
		return precoVenda;
		
	}
}
