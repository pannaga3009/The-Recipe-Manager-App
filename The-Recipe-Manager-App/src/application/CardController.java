package application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CardController {

	@FXML
    private Label recipeName;

    @FXML
    private HBox box;

    @FXML
    private Label chefName;

    @FXML
    private ImageView recipeImage;
    

    @FXML
    private Button MoreBtn;

    @FXML
    private HBox boxHome;


    @FXML
    private ImageView recipeRating;
   
    private Stage stage;
    private Scene scene;

    
    
    private String[] colors =  {"DCEDF2", "FFFFF"};
    
    public void setData(Recipe recipe) {
    	try {
    		System.out.println("Before--");
    		System.out.println("recipe.getImage()----"+recipe.getImage());
            Image image = new Image(recipe.getImage());
//            if (image == null || image.equals(null)) {
//                throw new Exception("Image not found");
//            }
          recipeImage.setImage(image);
            System.out.println("after--");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	recipeName.setText(recipe.getName());
    	chefName.setText(recipe.getchefName());
//    	box.setStyle("-fx-background-color:" + Color.web(colors[(int)(Math.random()*colors.length)]));
    	
    }
    
    public void setDataFromDb(Recipe recipe) throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String selectQuery = "SELECT * FROM RecipesInfo WHERE recipeName = ?";
        PreparedStatement selectStatement = connectDB.prepareStatement(selectQuery);
        selectStatement.setString(1, recipe.getName());
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            String Name = resultSet.getString("recipeName");
            String Description = resultSet.getString("recipeDescription");
            String Chef = resultSet.getString("recipeChef");
            double Rating = resultSet.getDouble("recipeRating");
            String Contents = resultSet.getString("recipeContents");
            byte[] recipeImg = resultSet.getBytes("recipesImg");

            Image image = new Image(new ByteArrayInputStream(recipeImg));
           
            // Set the retrieved data to the Recipe object
            recipe.setName(Name);
            recipe.setDescription(Description);
            recipe.setchefName(Chef);
            recipe.setRating(Rating);
            recipe.setContents(Contents);
            recipe.setImageDetail(image);

            // Set the data to the UI elements
            try {
                System.out.println("Before--");
//                System.out.println("recipe.getImage()----"+recipe.getImage());
                //Setting up the Image view here
                recipeImage.setImage(recipe.getImageDetail(image));
                System.out.println("after--");

            } catch (Exception e) {
                e.printStackTrace();
            }

            recipeName.setText(recipe.getName());
            chefName.setText(recipe.getchefName());
            
            if(recipe.getRating() >= 4.0) {
            	Image img = new Image("File:assets/Four_star.png");
            	recipeRating.setImage(img);
            	return;
            }
            else {
            	Image img = new Image("File:assets/Three_star.jpeg");
            	recipeRating.setImage(img);
            	return;
            }
            
        } else {
            System.out.println("Recipe not found in the database");
        }

        // Close the database connection
        connectDB.close();
    }

    
    
    @FXML
    void MoreBtnAction(ActionEvent event) throws IOException {
    	//adding new design for detail
    	Parent root = FXMLLoader.load(getClass().getResource("Detailcard.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

}
