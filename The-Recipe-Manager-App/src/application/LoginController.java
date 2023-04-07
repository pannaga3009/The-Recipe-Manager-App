package application;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController extends Login{

    
    @FXML 
    private Button loginButton;
    
    private Stage stage;
    private Scene scene;
    
    public void LoginPageBtAction(ActionEvent event) throws IOException {
    	if (usernameLogin.getText().isBlank() == false && passwordLogin.getText().isBlank() == false) {       		
    		LoginController lc = new LoginController();
    		if(lc.validateLogin(usernameLogin.getText(),passwordLogin.getText() ) == true) {
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
    public void BackBtnAction(ActionEvent event) throws IOException {
    		Parent root = FXMLLoader.load(getClass().getResource("OnboardingPage.fxml"));
	    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
    	
    }

}
