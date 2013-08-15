package estoque.client;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	
	private static final String BUNDLE_NAME = "estoque.client.messages"; 
	
	// Atributo locale para pegar o Idioma
    private static Locale locale = Locale.getDefault();
    
    // RESOURCE BUNDLE já cria uma lista com a chave e o valor
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME, locale); // Faz referência ao locale

	private Messages() {
	}

	public static void setLocale (Locale locale) {
		Messages.locale = locale;
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale);
	}
	public static String getString(String key) {
		
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static Locale getLocale() {
		return locale;
	}
}
