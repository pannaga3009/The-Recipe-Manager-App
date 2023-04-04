package application;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

	
	@FXML
	private HBox cardLayout;
	private List<Recipe> discoverRecipes;
	
	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		System.out.println("Entering home intializable");
		discoverRecipes = new ArrayList<>(discoverRecipes());
		try {
		for(int i =0; i < discoverRecipes.size(); i++)
		{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("card.fxml"));
			
			HBox cardBox = fxmlLoader.load();
			CardController cardController = fxmlLoader.getController();
			cardController.setData(discoverRecipes.get(i));
			cardLayout.getChildren().add(cardBox);
			
		}}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		}
	
	
	private List<Recipe> discoverRecipes(){
		List<Recipe> ls = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setName("Avocado\nToast");
		recipe.setImage("file:assets/avocado_toast.jpeg");
		recipe.setchefName("By Gordan Ramsay");
		
		ls.add(recipe);
	
		recipe = new Recipe();
		recipe.setName("Tofu\nCurry");
		recipe.setImage("file:assets/tofu_curry.jpeg");
		recipe.setchefName("By Jamie Oliver");
		
		ls.add(recipe);
		
		recipe = new Recipe();
		recipe.setName("Pink Sauce\nPasta");
		recipe.setImage("file:assets/pink_pasta.jpeg");
		recipe.setchefName("By Gordan Ramsay");
		
		ls.add(recipe);
		return ls;
		
	}
	
	
}
