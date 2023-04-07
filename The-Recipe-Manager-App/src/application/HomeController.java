package application;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;




public class HomeController implements Initializable {

	
	@FXML
	private HBox cardLayout;

	
	@FXML
	private HBox cardAreaLayout;
	
	@FXML 
	private Hyperlink mealPlan;
	
	private List<Recipe> discoverRecipes;
	private List<Recipe> addRecipesFromList;

	   
    private Stage stage;
    private Scene scene;

	
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
				
			}
			RecipeStorage rs = new RecipeStorage();
			addRecipesFromList = new ArrayList<>(addRecipes());
			rs.addRecipesToDb(addRecipesFromList);
			
			ArrayList<String> RecipeNames = new ArrayList<String>();
	
			RecipeNames.add("Paneer Tikka");
	
			RecipeNames.add("Veg Sandwich");
			RecipeNames.add("Salad Bowl");
		
		
			for(int i =0; i < RecipeNames.size(); i++)
			{
				Recipe rc = new Recipe();
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("Area.fxml"));
				
				HBox cardBox = fxmlLoader.load();
				CardController cardController = fxmlLoader.getController();
				rc.setName(RecipeNames.get(i));
				cardController.setDataFromDb(rc);

				cardController.sendObj(rc);
//				cardController.handleButtonClick(rc);
				cardAreaLayout.getChildren().add(cardBox);	
				
				
			}
	  

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
			
		}
	


	private List<Recipe> addRecipes() {
		List<Recipe> dbls = new ArrayList<>();
		Recipe recipe1 = new Recipe();
		recipe1.setName("Paneer Tikka");
		recipe1.setImage("assets/Paneer_tikka.jpeg");
		recipe1.setchefName("Pannaga Veeramohan");
		recipe1.setDescription("In a bowl, mix together ginger paste, garlic paste, red chili powder, coriander powder, cumin powder, turmeric powder, garam masala powder, and salt.\n"
				+ "Add the paneer cubes and vegetables to the bowl and mix well, making sure that the marinade coats everything evenly. Let it marinate for at least 30 minutes.\n"
				+ "Preheat the oven to 200°C (400°F). Skewer the paneer and vegetables.\n"
				+ "Brush some oil on the skewers and place them on a baking sheet.\n"
				+ "Bake for 10-12 minutes, turning them once halfway through cooking, until they are golden brown and slightly charred.\n");
		recipe1.setContents("250g paneer, cut into cubes\n"
				+ "1 bell pepper, cut into cubes\n"
				+ "1 onion, cut into cubes\n"
				+ "1 tomato, cut into cubes\n"
				+ "1 tsp ginger paste\n"
				+ "1 tsp garlic paste\n"
				+ "2 tsp red chili powder\n"
				+ "2 tsp coriander powder\n"
				+ "1 tsp cumin powder\n"
				+ "1/2 tsp turmeric powder\n"
				+ "1 tsp garam masala powder\n"
				+ "Salt to taste\n"
				+ "2 tbsp oil");
		recipe1.setRating(4.8);
		
		dbls.add(recipe1);
		
		
		Recipe recipe2 = new Recipe();
		recipe2.setName("Veg Sandwich");
		recipe2.setImage("assets/veg_sandwich.jpeg");
		recipe2.setchefName("John Smith");
		recipe2.setDescription("Take two bread slices and spread butter or mayonnaise on both the slices.\n"
		+ "Add sliced cucumber, tomato, onion and boiled potato on one of the bread slice.\n"
		+ "Sprinkle salt, black pepper powder and chaat masala.\n"
		+ "Cover the sandwich with the other bread slice.\n"
		+ "Heat a sandwich maker and place the sandwich in it.\n"
		+ "Toast the sandwich until it turns golden brown.\n");
		recipe2.setContents("4 bread slices\n"
		+ "1 small cucumber, sliced\n"
		+ "1 small tomato, sliced\n"
		+ "1 small onion, sliced\n"
		+ "1 boiled potato, sliced\n"
		+ "1/2 tsp salt\n"
		+ "1/4 tsp black pepper powder\n"
		+ "1/2 tsp chaat masala\n"
		+ "2 tbsp butter or mayonnaise");
		recipe2.setRating(4.5);

		dbls.add(recipe2);

		Recipe recipe3 = new Recipe();
		recipe3.setName("Salad Bowl");
		recipe3.setImage("assets/salad_bowl.jpeg");
		recipe3.setchefName("Mary Jones");
		recipe3.setDescription("In a large bowl, mix together chopped lettuce, spinach, carrots, cucumber, tomatoes and red onions.\n"
		+ "Add boiled chickpeas or any other beans of your choice.\n"
		+ "Add roasted sunflower seeds and pumpkin seeds for crunch.\n"
		+ "In a separate small bowl, whisk together olive oil, lemon juice, salt and black pepper.\n"
		+ "Pour the dressing over the salad and mix well.\n"
		+ "Garnish with some feta cheese or grated parmesan cheese and serve.");
		recipe3.setContents("2 cups chopped lettuce\n"
		+ "2 cups chopped spinach\n"
		+ "1 carrot, grated\n"
		+ "1 cucumber, chopped\n"
		+ "2 tomatoes, chopped\n"
		+ "1/2 red onion, sliced\n"
		+ "1 cup boiled chickpeas or any other beans\n"
		+ "2 tbsp roasted sunflower seeds\n"
		+ "2 tbsp roasted pumpkin seeds\n"
		+ "2 tbsp olive oil\n"
		+ "1 tbsp lemon juice\n"
		+ "1/2 tsp salt\n"
		+ "1/4 tsp black pepper powder\n"
		+ "2 tbsp crumbled feta cheese or grated parmesan cheese");
		recipe3.setRating(4.2);

		dbls.add(recipe3);

		return dbls;
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
	

	public void handleMealPlanClick(ActionEvent event) throws IOException {

		
	}
	public void handleSavedRecipesClick(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SavedRecipes.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
	

	
}
