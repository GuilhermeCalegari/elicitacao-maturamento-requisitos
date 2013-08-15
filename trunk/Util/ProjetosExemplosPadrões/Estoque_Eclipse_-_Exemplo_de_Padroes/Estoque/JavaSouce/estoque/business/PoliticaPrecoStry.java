package estoque.business;

import java.math.BigDecimal;

// Padrão Strategy : Interface Strategy
public interface PoliticaPrecoStry {
	BigDecimal algoritmoPreco(BigDecimal preco);
}