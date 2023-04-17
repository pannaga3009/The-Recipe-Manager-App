package application;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CustomRecipeCardController implements Initializable{

    @FXML
    private VBox box;
    
    @FXML
    private ImageView ImgSrc;

    @FXML
    private Label recipeContents;

    @FXML
    private Label recipeDescription;

    @FXML
    private ImageView recipeImg;


    @FXML
    private Label recipeName;

    @FXML
    private Label recipeTime;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	public void setRecipe(Recipe recipe) {
		recipeName.setText(recipe.getName());
	    recipeContents.setText(recipe.getContents());
	    recipeDescription.setText(recipe.getDescription());
	    recipeTime.setText(recipe.getPrepTime());
	   
	    
	    
	    try {
	       
	      
	        if (recipe.getImageFormat() == null) {
	            throw new Exception("Image not found");
	        }
	        
	      
            System.out.println("-----Inside custom recipe image printing----" + recipe.getImageFormat());

            recipeImg.setImage(recipe.getImageFormat());
           
            
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	    
       
		// TODO Auto-generated method stub
		
	}



	

}