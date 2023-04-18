package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAccount {
    // Fields for the recipe data
    private String recipeName;
    private String ingredients;
    public static int idUserAccount;
    public static ArrayList<Integer> userIdList = new ArrayList<>(); 
    public static int getUserId() {
        return idUserAccount;
    }

    public static void setUserId(int idUserAccount) {
       UserAccount.idUserAccount = idUserAccount;
    }
//
//    // Constructor to initialize the recipe data
//    public UserAccount(int idUserAccount) {
//        this.idUserAccount = idUserAccount;
//    }
  
    public static void addUserId(int idUserAccount) { 
    	userIdList.add(idUserAccount); 
    	} 
    public static ArrayList<Integer> getUserIdList() {
    		return userIdList;
//    	return idUserAccount;
    	}
    
    // Method to insert the recipe into the database
    public void insertRecipe() {
        // Get a connection to the database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
        // SQL query to insert a new recipe with the logged-in user's userId
        String insertRecipe = "INSERT INTO addmyrecipe (recipeName, ingredients, userId) VALUES (?, ?, ?)";
        
        try {
            // Create a prepared statement for the SQL query
            PreparedStatement preparedStatement = connectDB.prepareStatement(insertRecipe);
            
            // Set the parameters of the prepared statement to the recipe data
            preparedStatement.setString(1, recipeName);
            preparedStatement.setString(2, ingredients);
            preparedStatement.setInt(3, idUserAccount);
            
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
}
