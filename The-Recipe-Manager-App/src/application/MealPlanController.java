package application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MealPlanController implements Initializable {
	@FXML
    private VBox displayLayout;

	 @FXML
	 private RadioButton WeightLossButton;

	 @FXML
	  private RadioButton ketoButton;
	 
	 @FXML
	 private RadioButton CustomMealBtn;

    @FXML
    private RadioButton lowCarbButton;
    
    @FXML
    private Button create;
    
    private MealPlan mealPlan = new MealPlan();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addData();
        

        ToggleGroup toggleGroup = new ToggleGroup();
        ketoButton.setToggleGroup(toggleGroup);
        WeightLossButton.setToggleGroup(toggleGroup);
        lowCarbButton.setToggleGroup(toggleGroup);
        CustomMealBtn.setToggleGroup(toggleGroup);
           
        ketoButton.setOnAction(this::onRadioButtonSelected);
        WeightLossButton.setOnAction(this::onRadioButtonSelected);
        lowCarbButton.setOnAction(this::onRadioButtonSelected);
        CustomMealBtn.setOnAction(this::onRadioButtonSelected);

    }
    
    
    void onRadioButtonSelected(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) event.getSource();

        displayLayout.getChildren().clear();
        
        if (selectedRadioButton == WeightLossButton) {
            for (Recipe recipe : mealPlan.getRecipes("Weight Loss")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Diet.fxml"));
                    
                    VBox dietCard = fxmlLoader.load();

                    DietCardController dietCardController = fxmlLoader.getController();
                    if (dietCardController == null) {
                        dietCardController = new DietCardController();
                        fxmlLoader.setController(dietCardController);
                    }
                    dietCardController.setRecipe(recipe);

                    displayLayout.getChildren().add(dietCard);
                } catch (Exception e) {
                    System.out.println("Error caught");
                    e.printStackTrace();
                }
            }
            System.out.println("Weight loss meal plan selected");
        } else if (selectedRadioButton == ketoButton) {
            for (Recipe recipe : mealPlan.getRecipes("Keto")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Diet.fxml"));
                    
                    VBox dietCard = fxmlLoader.load();

                    DietCardController dietCardController = fxmlLoader.getController();
                    if (dietCardController == null) {
                        dietCardController = new DietCardController();
                        fxmlLoader.setController(dietCardController);
                    }
                    dietCardController.setRecipe(recipe);

                    displayLayout.getChildren().add(dietCard);
                } catch (Exception e) {
                    System.out.println("Error caught");
                    e.printStackTrace();
                }
            }
            System.out.println("Keto meal plan selected");
        } else if (selectedRadioButton == lowCarbButton) {
            for (Recipe recipe : mealPlan.getRecipes("Low Carb")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Diet.fxml"));
                    
                    VBox dietCard = fxmlLoader.load();

                    DietCardController dietCardController = fxmlLoader.getController();
                    if (dietCardController == null) {
                        dietCardController = new DietCardController();
                        fxmlLoader.setController(dietCardController);
                    }
                    dietCardController.setRecipe(recipe);

                    displayLayout.getChildren().add(dietCard);
                } catch (Exception e) {
                    System.out.println("Error caught");
                    e.printStackTrace();
                }
            }

            System.out.println("Low carb meal plan selected");
        }
        else if (selectedRadioButton == CustomMealBtn) {

        	    // Create a new database connection and retrieve the user ID
        	    DatabaseConnection connectNow = new DatabaseConnection();
        	    Connection connectDB = connectNow.getConnection();
        	    int userId = UserAccount.idUserAccount;

        	    try {
        	        // Retrieve all custom meal plans from the database
        	        PreparedStatement getallcustom = connectDB.prepareStatement("SELECT * FROM CustomMealPlan where idUserAccount = ?");
        	        getallcustom.setInt(1, userId);
        	        ResultSet customMealPlans = getallcustom.executeQuery();
        	        
        	       
        	        // Iterate over the custom meal plans
        	        while (customMealPlans.next()) {
        	            // Create a new Recipe object and set its properties based on the custom meal plan
        	        	Recipe customRecipe = new Recipe();
        	            customRecipe.setName(customMealPlans.getString("recipeName"));
        	            customRecipe.setContents(customMealPlans.getString("recipeContents"));
        	            customRecipe.setDescription(customMealPlans.getString("recipeDescription"));
        	            customRecipe.setPrepTime(customMealPlans.getString("prepTime"));
        	            
        	            byte[] recipeImg = customMealPlans.getBytes("recipeImage");
        	            Image image = new Image(new ByteArrayInputStream(recipeImg));
        	            
        	            customRecipe.setByteImage(recipeImg);
        	            customRecipe.setImageDetail(image);
        	            
        	            System.out.println("-----Inside meal plan image printing----" + image);

        	            // Add the custom recipe to the meal plan
        	            mealPlan.addRecipe("Custom Meal Plan", customRecipe);
        	        }
        	        
        	        for (Recipe recipe : mealPlan.getRecipes("Custom Meal Plan")) {
                       
        	            // Create a new CustomRecipeCardController and add its corresponding view to the display layout
        	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomRecipeCard.fxml"));
        	            VBox customRecipeCardView = null;
						try {
							customRecipeCardView = fxmlLoader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        	            CustomRecipeCardController customCardController = fxmlLoader.getController();
        	            if (customCardController == null) {
        	                customCardController = new CustomRecipeCardController();
        	                fxmlLoader.setController(customCardController);
        	            }
        	            customCardController.setRecipe(recipe);
        	            displayLayout.getChildren().add(customRecipeCardView);
        	        }

        	        // Close the database connection
        	        connectDB.close();
        	    } catch (SQLException e) {
        	        e.printStackTrace();
        	    }

        	    // Print a message indicating that the custom meal button was selected
        	    System.out.println("Custom Meal Btn plan selected");
        	}

    }

    
    

    
    private void addData() {
   	 // Initialize some recipes for each meal plan
	   Recipe lowCarbRecipe1 = new Recipe();
	   lowCarbRecipe1.setName("Low Carb Chicken Parmesan");
	   lowCarbRecipe1.setchefName("Jane Doe");
	   lowCarbRecipe1.setImage("File:assets/lowcarb_chicken_par.jpeg");
	   lowCarbRecipe1.setRating(4.5);
	   lowCarbRecipe1.setPrepTime("30 mins");
	   lowCarbRecipe1.setDescription("A low carb version of the classic Italian dish\n made with chicken breast, tomato sauce, and mozzarella cheese.");
	   lowCarbRecipe1.setContents("Ingredients: 4 boneless, skinless chicken breasts, \n1 cup almond flour, 1 tsp Italian seasoning, \n1 tsp garlic powder, 1/2 tsp salt, 1/4 tsp black pepper, 1 egg, beaten, 1/4 cup olive oil, 1 cup marinara sauce, 1 cup shredded mozzarella cheese, 1/4 cup grated Parmesan cheese.");
	   
       Recipe lowCarbRecipe2 = new Recipe();
       lowCarbRecipe2.setName("Low Carb Cauliflower Fried Rice");
       lowCarbRecipe2.setchefName("Chef 2");
       lowCarbRecipe2.setImage("File:assets/lowcarb_cauli.jpeg");
       lowCarbRecipe2.setRating(4.0);
       lowCarbRecipe2.setPrepTime("20 mins");
       lowCarbRecipe2.setDescription("This is a delicious low carb version\n of traditional fried rice made \n with cauliflower rice.");
       lowCarbRecipe2.setContents("Ingredients: cauliflower rice, eggs, \npeas, carrots, soy sauce, \ngarlic, ginger");

       Recipe ketoRecipe1 = new Recipe();
       ketoRecipe1.setName("Keto Bacon-Wrapped Chicken");
       ketoRecipe1.setchefName("Chef 3");
       ketoRecipe1.setImage("File:assets/keto_recipe1.jpeg");
       ketoRecipe1.setRating(4.2);
       ketoRecipe1.setPrepTime("40 mins");
       ketoRecipe1.setDescription("This is a delicious keto recipe featuring \nbacon-wrapped chicken breasts with a creamy cheese filling.");
       ketoRecipe1.setContents("Ingredients: chicken breasts, bacon, \ncream cheese, cheddar cheese, \ngarlic, paprika");

       Recipe ketoRecipe2 = new Recipe();
       ketoRecipe2.setName("Keto Chicken Alfredo");
       ketoRecipe2.setchefName("Chef John");
       ketoRecipe2.setImage("File:assets/keto_chicken_alfredo.jpeg");
       ketoRecipe2.setRating(4.8);
       ketoRecipe2.setPrepTime("50 mins");
       ketoRecipe2.setDescription("This is a delicious keto-friendly\n version of Chicken Alfredo");
       ketoRecipe2.setContents("Ingredients: chicken breast, heavy cream,\n parmesan cheese, garlic, butter, salt, black pepper");


       Recipe weightLossRecipe1 = new Recipe();
       weightLossRecipe1.setName("Weight Loss Spicy Chicken Salad");
       weightLossRecipe1.setchefName("Chef 5");
       weightLossRecipe1.setImage("File:assets/chicken_salad.jpeg");
       weightLossRecipe1.setRating(3.8);
       weightLossRecipe1.setPrepTime("30 mins");
       weightLossRecipe1.setDescription("This is a delicious weight loss recipe\n featuring a spicy grilled chicken breast over a bed \nof mixed greens and veggies.");
       weightLossRecipe1.setContents("Ingredients: chicken breast, mixed greens, tomatoes, \ncucumber, red onion, avocado, \nlime, jalapeno");
       
       Recipe weightLossRecipe2 = new Recipe();
       weightLossRecipe2.setName("Quinoa Salad with Avocado and Mango");
       weightLossRecipe2.setchefName("Chef Sarah");
       weightLossRecipe2.setImage("File:assets/quinoa_salad.jpeg");
       weightLossRecipe2.setRating(4.6);
       weightLossRecipe2.setPrepTime("15 mins");
       weightLossRecipe2.setDescription("This is a delicious weight loss recipe\n packed with healthy ingredients");
       weightLossRecipe2.setContents("Ingredients: quinoa, avocado, mango, cucumber\n, red onion, cilantro, lime juice, olive oil,\nsalt, black pepper");

       Recipe weightLossRecipe3 = new Recipe();
       weightLossRecipe3.setName("Grilled Fish with Vegetables");
       weightLossRecipe3.setchefName("Chef Alex");
       weightLossRecipe3.setImage("File:assets/grilled_fish.jpeg");
       weightLossRecipe3.setRating(4.2);
       weightLossRecipe3.setPrepTime("30 mins");
       weightLossRecipe3.setDescription("This is a healthy and delicious\n weight loss recipe");
       weightLossRecipe3.setContents("Ingredients: fish fillets, zucchini, bell pepper,\n onion, garlic, lemon juice, olive oil, \nsalt, black pepper");

       mealPlan.addRecipe("Low Carb", lowCarbRecipe1);
       mealPlan.addRecipe("Low Carb", lowCarbRecipe2);
       mealPlan.addRecipe("Keto", ketoRecipe1);
       mealPlan.addRecipe("Keto", ketoRecipe2);
       mealPlan.addRecipe("Weight Loss", weightLossRecipe1);
       mealPlan.addRecipe("Weight Loss", weightLossRecipe2);
       mealPlan.addRecipe("Weight Loss", weightLossRecipe3);
		
	}


	public void backBtnAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
   

@FXML
public void createBtnAction(ActionEvent event) throws IOException {
	
	 FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateMealPlan.fxml"));
     Parent root = loader.load();
    
     
     CreatePlanMeal controller = loader.getController();
     
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
}
	
}