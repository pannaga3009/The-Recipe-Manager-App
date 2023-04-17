package application;

import java.io.ByteArrayInputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CustomRecipeCardController {

    @FXML
    private VBox box;

    @FXML
    private Label recipeContents;

    @FXML
    private Label recipeDescription;

    @FXML
    private ImageView recipeImage;

    @FXML
    private Label recipeName;

    @FXML
    private Label recipeTime;
    


	public void setRecipe(Recipe recipe) {
		recipeName.setText(recipe.getName());
	    recipeContents.setText(recipe.getContents());
	    recipeDescription.setText(recipe.getDescription());
	    recipeTime.setText(recipe.getPrepTime());
	   
	    byte[] imageData = recipe.getByteImage();
	    
	    try {
	        // Get the image data as a byte array
	      
	        if (imageData == null) {
	            throw new Exception("Image not found");
	        }
	        
	        // Create an Image object from the byte array
	        Image image = new Image(new ByteArrayInputStream(imageData));
            System.out.println("-----Inside custom recipe image printing----" + recipe.getImageFormat());

	       recipeImage.setImage(recipe.getImageFormat());
	        recipeImage.setFitWidth(200);
	        recipeImage.setFitHeight(200);
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	    
       
		// TODO Auto-generated method stub
		
	}

}