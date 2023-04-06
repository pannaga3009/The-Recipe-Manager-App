package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SavedRecipesController implements Initializable {

    @FXML
    private HBox cardAreaLayout;

    @FXML
    private HBox cardLayout;

    @FXML
    private Hyperlink mealPlan;

    @FXML
    private Hyperlink savedRecipes;
	private List<Recipe> saveRecipes;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		/*
		 * saveRecipes = new ArrayList<>(); try { for(int i =0; i <
		 * discoverRecipes.size(); i++) { FXMLLoader fxmlLoader = new FXMLLoader();
		 * fxmlLoader.setLocation(getClass().getResource("Area.fxml"));
		 * 
		 * HBox cardBox = fxmlLoader.load(); CardController cardController =
		 * fxmlLoader.getController(); cardController.setData(discoverRecipes.get(i));
		 * cardLayout.getChildren().add(cardBox); cardBox.setOnMouseClicked(event ->{
		 * try { Parent root =
		 * FXMLLoader.load(getClass().getResource("Detailcard.fxml")); Stage stage = new
		 * Stage(); stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 * Scene scene = new Scene(root, 300, 200); stage.setScene(scene); stage.show();
		 * }catch(IOException e){ e.printStackTrace(); } }); } } catch(Exception e) {
		 * e.printStackTrace(); }
		 */
	}
    
}
