package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController{

	@FXML
	private Label LoginMessageLabel;
    
    @FXML
    private TextField usernameLogin;
    
    @FXML
    private PasswordField passwordLogin;
   
    
    @FXML 
    private Button loginButton;
    
    private Stage stage;
    private Scene scene;
    
    public void LoginPageBtAction(ActionEvent event) throws IOException {
    	if (usernameLogin.getText().isBlank() == false && passwordLogin.getText().isBlank() == false) {   		
    		if(validateLogin() == true) {
    		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
	    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
    		}
    		
    	}else {
    		LoginMessageLabel.setText("Please Enter Username and Password");
    	}
    	
    }
    
    public boolean validateLogin() {
    	DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
    	System.out.println("username "+ usernameLogin.getText() + " AND password = "+ passwordLogin.getText());
    	String verifyLogin = "SELECT count(1) from UserAccount where userName = '" + usernameLogin.getText() + "'  AND password = '"+ passwordLogin.getText() + "'";
    	System.out.println(verifyLogin);
    	try {
    		Statement statement = connectDB.createStatement();
    		ResultSet queryResult = statement.executeQuery(verifyLogin);
    		
    		while(queryResult.next()) {
    			int count = queryResult.getInt(1);
    		    System.out.println("Count: " + count);
    			if(queryResult.getInt(1) == 1) {
    				LoginMessageLabel.setText("Welcome");
    				return true;
    			}else {
    				LoginMessageLabel.setText("Invalid login. Please try again");
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
