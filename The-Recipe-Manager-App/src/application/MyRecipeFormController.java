package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import application.Login;


public class MyRecipeFormController extends MyRecipeForm {
	 private int userId;

	    

    @Override
    void handleCancelButton(ActionEvent event) {
    	// Get the stage (i.e., window) associated with the current event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current window
        stage.close();

        // Go back to the previous page
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Myprofile.fxml"));
			Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    void handleSubmitButton(ActionEvent event){
        // Add your implementation for the submit button click event

  	  String recipeNameText = recipeName.getText();
        String ingredientsText = ingredients.getText();
        String stepsToCookText = stepsToCook.getText();
    
        String dietType = "";
        
        if (vegetarianDietType.isSelected()) {
            dietType = vegetarianDietType.getText();
        } else if (eggetarianDietType.isSelected()) {
            dietType = eggetarianDietType.getText();
        } else if (nonvegetarianDietType.isSelected()) {
            dietType = nonvegetarianDietType.getText();
        }
        

      
        System.out.println("Recipe Name: " + recipeNameText);
        System.out.println("Ingredients: " + ingredientsText);
        System.out.println("Steps to Cook: " + stepsToCookText);
        System.out.println("Diet Type: " + dietType);
      

        
        // Get the stage (i.e., window) associated with the current event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current window
        stage.close();

        // Go back to the previous page
        Parent root;
		try {
			// Get the stage (i.e., window) associated with the current event
	        
				root = FXMLLoader.load(getClass().getResource("Myprofile.fxml"));
				Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		        
		        
//	Get the userId
//		      write Sql query to get it from the table
//		        I have to send the data to mydisplaycardcontroller

		  
		        insert();
		     


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

    }

    public void insert() {
		// TODO Auto-generated method stub
    	// Get a connection to the database
    	
    	int userId = UserAccount.idUserAccount;
    	
    	System.out.println("Entering Method insert--------------------------");
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
        // SQL query to insert a new recipe with the logged-in user's userId
        String insertRecipe = "INSERT INTO addmyrecipe (recipeName, ingredients, stepsToCook, idUserAccount) VALUES (?,?,?,?)";
        
        try {
            // Create a prepared statement for the SQL query
            PreparedStatement preparedStatement = connectDB.prepareStatement(insertRecipe);
            System.out.println("UserID " + userId);
        
            // Set the parameters of the prepared statement to the recipe data
            preparedStatement.setString(1, recipeName.getText());
            preparedStatement.setString(2, ingredients.getText());
            preparedStatement.setString(3, stepsToCook.getText());
            preparedStatement.setInt(4, userId);
            
            // Execute the prepared statement to insert the new recipe
            preparedStatement.executeUpdate();
            
            // Print a message to confirm that the recipe was inserted successfully
            System.out.println("Recipe inserted successfully!");
        } catch (SQLException e) {
            // Print an error message if the recipe could not be inserted
            System.out.println("Error inserting recipe: " + e.getMessage());
        } finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
            }
        }
		
	}

	@Override
    void handleDietType(ActionEvent event) {
        // Add your implementation for the vegetarian radio button click event
        System.out.println("DietType radio button clicked");
    }
 
}
