package application;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javafx.stage.Stage;



public class CardController implements Initializable {


	@FXML
    private Label recipeName;

    @FXML
    private HBox box;

    @FXML
    private Label chefName;

    @FXML
    private ImageView recipeImage;
    


    @FXML
    private Button MoreBtn = new Button("More details");;

    @FXML
    private HBox boxHome;


    @FXML
    private ImageView recipeRating;
    
    @FXML
    private Label chefAreaName;

    @FXML
    private ImageView recipeAreaImage;

    @FXML
    private Label recipeAreaName;

    @FXML
    private ImageView recipeAreaRating;
    
    @FXML
    private ImageView DetailrecipeImage;

    @FXML
    private Button backbtn;

    @FXML
    private Label chefNameDetail;

    @FXML
    private Button commentsbtn;

    @FXML
    private Label recipeContentsDetail;

    @FXML
    private Label recipeDescriptionDetail;

    @FXML
    private Label recipeNameDetailTo;

    @FXML
    private ImageView recipeRatingDetail;

   
    private Stage stage;
    private Scene scene;

    


    private String[] colors =  {"DCEDF2", "FFFFF"};
    
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

        DetailrecipeImage = new ImageView();
        recipeNameDetailTo = new Label();
        chefNameDetail = new Label();
        recipeDescriptionDetail = new Label();
        recipeContentsDetail = new Label();
        recipeRatingDetail = new ImageView();
        
        
        
        
		
	}
    
    public void sendObj(Recipe recipe) {
        MoreBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Detailcard.fxml"));
                Parent root = loader.load();
                DetailCardController controller = loader.getController();
                controller.handleButtonClick(recipe);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }

    
   public void MoreBtnAction(ActionEvent e) {
	   
   }
    
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

    
    public void setDataFromDb(Recipe recipe) throws SQLException {
        
      
        String selectQuery = "SELECT * FROM RecipesInfo WHERE recipeName = ?";
        PreparedStatement selectStatement = connectDB.prepareStatement(selectQuery);
        selectStatement.setString(1, recipe.getName());
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            String Name = resultSet.getString("recipeName");
            String Description = resultSet.getString("recipeDescription");
            String Chef = resultSet.getString("recipeChef");
            double Rating = resultSet.getDouble("recipeRating");
            String Contents = resultSet.getString("recipeContents");
            byte[] recipeImg = resultSet.getBytes("recipesImg");

            Image image = new Image(new ByteArrayInputStream(recipeImg));
           
            // Set the retrieved data to the Recipe object
            recipe.setName(Name);
            recipe.setDescription(Description);
            recipe.setchefName(Chef);
            recipe.setRating(Rating);
            recipe.setContents(Contents);
            recipe.setImageDetail(image);

            // Set the data to the UI elements
           
            try {
            	
                System.out.println("Before--");
                System.out.println("recipe.getImage()----"+recipe.getImageDetail(image));
                //Setting up the Image view here
                recipeAreaImage.setImage(recipe.getImageDetail(image));
                System.out.println("after--");

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Recipe  name ----"+recipe.getName());
            System.out.println("Recipe  chef ---"+recipe.getchefName());
            recipeAreaName.setText(recipe.getName());
            chefAreaName.setText(recipe.getchefName());
            
            if(recipe.getRating() >= 4.0) {
            	Image img = new Image("File:assets/Four_star.png");
            	recipeAreaRating.setImage(img);
            	return;
            }
            else {
            	Image img = new Image("File:assets/Three_star.jpeg");
            	recipeAreaRating.setImage(img);
            	return;
            }
            
           
            
        } else {
            System.out.println("Recipe not found in the database");
        }

        // Close the database connection
        
    }

	

	
    void handleButtonClick(Recipe recipe) throws SQLException{
	    // do something with the object
    	 
     

}



}
