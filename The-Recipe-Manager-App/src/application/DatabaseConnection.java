package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public Connection databaseLink;
	
	public Connection getConnection() {
		String databaseName = "Datahub_Dev";
		String databaseUser = "root";
		String databasePassword = "Pass3009@";
		
		String url = "jdbc:mysql://localhost/" + databaseName;
		
		
		try {
			Class.forName("com.sql.cj.jdbc.driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return databaseLink;
	}
}
