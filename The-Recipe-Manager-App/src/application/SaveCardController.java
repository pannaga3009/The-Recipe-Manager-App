package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SaveCardController implements Initializable{

    @FXML
    private Label RecipeName;

    @FXML
    private ImageView ratingImage;

    @FXML
    private Label recipeContents;

    @FXML
    private Label recipeDescription;

    @FXML
    private Label savechefName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		RecipeName = new Label();
		ratingImage = new ImageView();
		recipeContents = new Label();
		recipeDescription = new Label();
		savechefName = new Label();
		
		
		
	}
	
	

}
