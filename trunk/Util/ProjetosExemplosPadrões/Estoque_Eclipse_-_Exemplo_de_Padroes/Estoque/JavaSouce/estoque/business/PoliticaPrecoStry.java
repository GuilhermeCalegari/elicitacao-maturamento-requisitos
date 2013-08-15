package estoque.business;

import java.math.BigDecimal;

// Padr�o Strategy : Interface Strategy
public interface PoliticaPrecoStry {
	BigDecimal algoritmoPreco(BigDecimal preco);
}