package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



public class DietCardController implements Initializable{
	@FXML
    private HBox box;

    @FXML
    private Label recipeChef;

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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	public void setRecipe(Recipe recipe) {
		
		recipeName.setText(recipe.getName());
	    recipeChef.setText(recipe.getchefName());
	    recipeContents.setText(recipe.getContents());
	    recipeDescription.setText(recipe.getDescription());
	   // recipeTime.setText(recipe.getTime());
	    Image image = new Image(recipe.getImage());
	    recipeImage.setImage(image);
		
	}
	

}
