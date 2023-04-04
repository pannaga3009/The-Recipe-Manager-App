package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CardController {

	@FXML
    private Label recipeName;

    @FXML
    private HBox box;

    @FXML
    private Label chefName;

    @FXML
    private ImageView recipeImage;
    
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

}
