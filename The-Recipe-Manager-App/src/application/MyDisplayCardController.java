package application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MyDisplayCardController extends MyDisplayCard implements Initializable{
    
	 DatabaseConnection connectNow = new DatabaseConnection();
     Connection connectDB = connectNow.getConnection();

	 @FXML
	    private Button deleteButton;
	 
	    @FXML
	    private Label ingredients;

	    @FXML
	    private Label stepsToCook;

	    @FXML
	    private Label recipeName;
	    
	    void displayRecipe() throws SQLException {
      
		   System.out.println("Displaying recipe inside my displaycard ---- ");
	       String selectQuery = "SELECT * FROM addmyrecipe WHERE idUserAccount = ?";
	       PreparedStatement selectStatement = connectDB.prepareStatement(selectQuery);
	       ArrayList<Integer> userIdList = UserAccount.getUserIdList();
	       int userId = UserAccount.idUserAccount;
	       selectStatement.setInt(1, userId);
	       ResultSet resultSet = selectStatement.executeQuery();
	       Recipe recipe = new Recipe();
	       if (resultSet.next()) {
	           String Name = resultSet.getString("recipeName");
	           String Ingredients = resultSet.getString("ingredients");
	           String StepsToCook = resultSet.getString("stepsToCook");
	           String recipeType = resultSet.getString("recipeType");
	           
	
	         
	          
	           // Set the retrieved data to the Recipe object
	           recipe.setName(Name);
	           recipe.setDescription(StepsToCook);
	           recipe.setContents(Ingredients);
	           
	
	           // Set the data to the UI elements
	           recipeName.setText(recipe.getName());
	           stepsToCook.setText(recipe.getDescription());
	           ingredients.setText(Ingredients);
           
       }  
           
           	

   }
    
	@Override
	void handleViewClick(ActionEvent event) {
	

	 	// Get the stage (i.e., window) associated with the current event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current window
        stage.close();

        // Go back to the previous page
        Parent root;
		try {
			// Get the stage (i.e., window) associated with the current event
	        
				root = FXMLLoader.load(getClass().getResource("ViewRecipe.fxml"));
				Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		        


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	    
	
    @Override
	void handleDeleteClick(){

 	   System.out.println("Deleting recipe inside my displaycard ---- ");
    	    String deleteQuery = "DELETE FROM addmyrecipe WHERE idUserAccount = ? AND recipeName = ?";
    	    PreparedStatement deleteStatement = null;
			try {
				deleteStatement = connectDB.prepareStatement(deleteQuery);
				deleteStatement.setInt(1, UserAccount.idUserAccount);
				deleteStatement.setString(2, recipeName.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	   
    	   
    	    int affectedRows;
			try {
				affectedRows = deleteStatement.executeUpdate();
				 if (affectedRows > 0) {
		    	        Alert alert = new Alert(AlertType.INFORMATION);
		    	        alert.setTitle("Delete Recipe");
		    	        alert.setHeaderText("Recipe Name: " + recipeName);
		    	        alert.setContentText("Recipe deleted successfully!");
		    	        alert.showAndWait();
		    	    } else {
		    	        Alert alert = new Alert(AlertType.ERROR);
		    	        alert.setTitle("Error");
		    	        alert.setHeaderText(null);
		    	        alert.setContentText("Unable to delete recipe. Please try again.");
		    	        alert.showAndWait();
		    	    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			displayRecipe();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		 // Set the retrieved data to the Recipe object
		System.out.println("Inside My display controller - set Recipe ----------------------");

        // Set the data to the UI elements

        recipeName.setText(recipe.getName());
        stepsToCook.setText(recipe.getDescription());
        ingredients.setText(recipe.getContents());

	}
    
}
