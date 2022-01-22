package foodApp;
//connection to database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static final String SCHEMA = "food";
	private static final String USERNAME = "food";
	private static final String PASSWORD = "food";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	
	
	static Connection getConnection() {
		String uri = String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, SCHEMA);
		
		try {
			return DriverManager.getConnection(uri, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException("Unable to get connection.", e);
		}
	}
}
