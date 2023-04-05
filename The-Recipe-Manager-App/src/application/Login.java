package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public abstract class Login {
	
	
	@FXML
	protected Label LoginMessageLabel;
    
    @FXML
    protected TextField usernameLogin;
    
    @FXML
    protected PasswordField passwordLogin;
    
	protected boolean validateLogin(String username, String password) {
    	DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
    	System.out.println("username "+ username + " AND password = "+ password);
    	String verifyLogin = "SELECT count(1) from UserAccount where userName = '" + username + "'  AND password = '"+ password + "'";
    	System.out.println(verifyLogin);
    	try {
    		Statement statement = connectDB.createStatement();
    		ResultSet queryResult = statement.executeQuery(verifyLogin);
    		
    		while(queryResult.next()) {
    			int count = queryResult.getInt(1);
    		    System.out.println("Count: " + count);
    			if(queryResult.getInt(1) == 1) {
    				return true;
    			}else {
    				return false;
    			}
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		return false;
    }
}