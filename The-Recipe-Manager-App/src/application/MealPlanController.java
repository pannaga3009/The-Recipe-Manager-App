package application;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class MealPlanController implements Initializable {
	@FXML
    private VBox displayLayout;

	 @FXML
	 private RadioButton WeightLossButton;

	 @FXML
	  private RadioButton ketoButton;

    @FXML
    private RadioButton lowCarbButton;
    
    private MealPlan mealPlan = new MealPlan();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addData();

        ToggleGroup toggleGroup = new ToggleGroup();
        ketoButton.setToggleGroup(toggleGroup);
        WeightLossButton.setToggleGroup(toggleGroup);
        lowCarbButton.setToggleGroup(toggleGroup);
           
        ketoButton.setOnAction(this::onRadioButtonSelected);
        WeightLossButton.setOnAction(this::onRadioButtonSelected);
        lowCarbButton.setOnAction(this::onRadioButtonSelected);

    }
    
    
    
    
    
    void onRadioButtonSelected(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) event.getSource();

        if (selectedRadioButton == WeightLossButton) {
        	for (Recipe recipe : mealPlan.getRecipes("Weight Loss")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DietCard.fxml"));
                    VBox dietCard = fxmlLoader.load();

                    DietCardController dietCardController = fxmlLoader.getController();
                    dietCardController.setRecipe(recipe);

                    displayLayout.getChildren().add(dietCard);
                }catch(Exception e) {
             	   System.out.println("Error caught");
                }
            }
            System.out.println("Weight loss meal plan selected");
        } else if (selectedRadioButton == ketoButton) {
        	for (Recipe recipe : mealPlan.getRecipes("Keto")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DietCard.fxml"));
                    VBox dietCard = fxmlLoader.load();

                    DietCardController dietCardController = fxmlLoader.getController();
                    dietCardController.setRecipe(recipe);

                    displayLayout.getChildren().add(dietCard);
                }catch(Exception e) {
             	   System.out.println("Error caught");
                }
            }
            System.out.println("Keto meal plan selected");
        } else if (selectedRadioButton == lowCarbButton) {
        	for (Recipe recipe : mealPlan.getRecipes("Low Carb")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DietCard.fxml"));
                    VBox dietCard = fxmlLoader.load();

                    DietCardController dietCardController = fxmlLoader.getController();
                    dietCardController.setRecipe(recipe);

                    displayLayout.getChildren().add(dietCard);
                }catch(Exception e) {
             	   System.out.println("Error caught");
                }
            }

            System.out.println("Low carb meal plan selected");
        }
    }
    
    private void addData() {
   	 // Initialize some recipes for each meal plan
	   Recipe lowCarbRecipe1 = new Recipe();
	   lowCarbRecipe1.setName("Low Carb Chicken Parmesan");
	   lowCarbRecipe1.setchefName("Jane Doe");
	   lowCarbRecipe1.setImage("File:assets/lowcarb_chicken_par.jpeg");
	   lowCarbRecipe1.setRating(4.5);
	   lowCarbRecipe1.setDescription("A low carb version of the classic Italian dish made with chicken breast, tomato sauce, and mozzarella cheese.");
	   lowCarbRecipe1.setContents("Ingredients: 4 boneless, skinless chicken breasts, 1 cup almond flour, 1 tsp Italian seasoning, 1 tsp garlic powder, 1/2 tsp salt, 1/4 tsp black pepper, 1 egg, beaten, 1/4 cup olive oil, 1 cup marinara sauce, 1 cup shredded mozzarella cheese, 1/4 cup grated Parmesan cheese.");
	   
       Recipe lowCarbRecipe2 = new Recipe();
       lowCarbRecipe2.setName("Low Carb Cauliflower Fried Rice");
       lowCarbRecipe2.setchefName("Chef 2");
       lowCarbRecipe2.setImage("File:assets/lowcarb_cauli.jpeg");
       lowCarbRecipe2.setRating(4.0);
       lowCarbRecipe2.setDescription("This is a delicious low carb version of traditional fried rice made with cauliflower rice.");
       lowCarbRecipe2.setContents("Ingredients: cauliflower rice, eggs, peas, carrots, soy sauce, garlic, ginger");

       Recipe ketoRecipe1 = new Recipe();
       ketoRecipe1.setName("Keto Bacon-Wrapped Chicken");
       ketoRecipe1.setchefName("Chef 3");
       ketoRecipe1.setImage("File:assets/keto_recipe1.jpeg");
       ketoRecipe1.setRating(4.2);
       ketoRecipe1.setDescription("This is a delicious keto recipe featuring bacon-wrapped chicken breasts with a creamy cheese filling.");
       ketoRecipe1.setContents("Ingredients: chicken breasts, bacon, cream cheese, cheddar cheese, garlic, paprika");

       Recipe ketoRecipe2 = new Recipe();
       ketoRecipe2.setName("Keto Chicken Alfredo");
       ketoRecipe2.setchefName("Chef John");
       ketoRecipe2.setImage("File:assets/keto_chicken_alfredo.jpeg");
       ketoRecipe2.setRating(4.8);
       ketoRecipe2.setDescription("This is a delicious keto-friendly version of Chicken Alfredo");
       ketoRecipe2.setContents("Ingredients: chicken breast, heavy cream, parmesan cheese, garlic, butter, salt, black pepper");


       Recipe weightLossRecipe1 = new Recipe();
       weightLossRecipe1.setName("Weight Loss Spicy Chicken Salad");
       weightLossRecipe1.setchefName("Chef 5");
       weightLossRecipe1.setImage("File:assets/chicken_salad.jpeg");
       weightLossRecipe1.setRating(3.8);
       weightLossRecipe1.setDescription("This is a delicious weight loss recipe featuring a spicy grilled chicken breast over a bed of mixed greens and veggies.");
       weightLossRecipe1.setContents("Ingredients: chicken breast, mixed greens, tomatoes, cucumber, red onion, avocado, lime, jalapeno");
       
       Recipe weightLossRecipe2 = new Recipe();
       weightLossRecipe2.setName("Quinoa Salad with Avocado and Mango");
       weightLossRecipe2.setchefName("Chef Sarah");
       weightLossRecipe2.setImage("File:assets/quinoa_salad.jpeg");
       weightLossRecipe2.setRating(4.6);
       weightLossRecipe2.setDescription("This is a delicious weight loss recipe packed with healthy ingredients");
       weightLossRecipe2.setContents("Ingredients: quinoa, avocado, mango, cucumber, red onion, cilantro, lime juice, olive oil, salt, black pepper");

       Recipe weightLossRecipe3 = new Recipe();
       weightLossRecipe3.setName("Grilled Fish with Vegetables");
       weightLossRecipe3.setchefName("Chef Alex");
       weightLossRecipe3.setImage("File:assets/grilled_fish.jpeg");
       weightLossRecipe3.setRating(4.2);
       weightLossRecipe3.setDescription("This is a healthy and delicious weight loss recipe");
       weightLossRecipe3.setContents("Ingredients: fish fillets, zucchini, bell pepper, onion, garlic, lemon juice, olive oil, salt, black pepper");

       mealPlan.addRecipe("Low Carb", lowCarbRecipe1);
       mealPlan.addRecipe("Low Carb", lowCarbRecipe2);
       mealPlan.addRecipe("Keto", ketoRecipe1);
       mealPlan.addRecipe("Keto", ketoRecipe2);
       mealPlan.addRecipe("Weight Loss", weightLossRecipe1);
       mealPlan.addRecipe("Weight Loss", weightLossRecipe2);
       mealPlan.addRecipe("Weight Loss", weightLossRecipe3);
		
	}


	
    


	
}