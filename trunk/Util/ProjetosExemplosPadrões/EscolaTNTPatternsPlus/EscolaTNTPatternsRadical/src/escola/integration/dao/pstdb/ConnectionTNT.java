package escola.integration.dao.pstdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Padrão Singleton 
 * 
 */
public class ConnectionTNT {

	private final static String DRIVER = "org.postgresql.Driver";
	private final static String URL = "jdbc:postgresql://localhost:5432/dbEscola";
	private final static String USER = "postgres";
	private final static String PASSWD = "postgres";

	private Connection connection = null;

	private static ConnectionTNT instance = null;

	private ConnectionTNT() {
		inicializar();
	}

	public static ConnectionTNT getInstance() {
		if (instance == null)
			instance = new ConnectionTNT();
		return instance;
	}

	private void inicializar() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWD);
		} catch (ClassNotFoundException e) {
			System.out.println("O driver nao pode ser carregado ["
					+ e.getMessage() + "]");
		} catch (SQLException e) {
			System.out.println("A conexao nao pode ser estabelecida ["
					+ e.getMessage() + "]");
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar a conexao [" + e.getMessage()
					+ "]");
		}
	}
}
