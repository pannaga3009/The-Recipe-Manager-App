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
import javafx.stage.Stage;

public class DetailCardController implements Initializable{
	
	 DatabaseConnection connectNow = new DatabaseConnection();
	 Connection connectDB = connectNow.getConnection();
	 
	@FXML
	private ImageView DetailrecipeImage;

//	@FXML
//	private Label recipeNameDetail;

	@FXML
	private Label chefNameDetail;

	@FXML
	private Label recipeDescriptionDetail;

	@FXML
	private Label recipeContentsDetail;

	@FXML
	private ImageView recipeRatingDetail;

	@FXML
	private Button backbtn;

	@FXML
	private Label recipeNameDetailTo;
	
	@FXML
	private Label Mymsg;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	   
	}

	public void initData(Recipe recipe) {
	  
	    try {
	    	System.out.println("----------Inside INIT DATA----------------");
	        System.out.println("Get name"+ recipe.getName() );
	        System.out.println("Get chef name"+ recipe.getchefName() );
	        System.out.println("Get recipe name detail"+ recipe.getDescription() );
	        System.out.println("Get recipe contents"+ recipe.getContents());
	        System.out.println("**************************"+recipeNameDetailTo);
	        
	        System.out.println("Mymsg------" + Mymsg);
	        Mymsg.setText("Hello");
	        recipeNameDetailTo.setText(recipe.getName());
	        chefNameDetail.setText(recipe.getchefName());
	        recipeDescriptionDetail.setText(recipe.getDescription());
	        recipeContentsDetail.setText(recipe.getContents());

	        if(recipe.getRating() >= 4.0) {
   			 recipeRatingDetail.setImage(new Image("File:assets/Four_star.png"));
            	return;
            }
            else {
            	
           	 recipeRatingDetail.setImage(new Image("File:assets/Three_star.jpeg"));
            	return;
            }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	public void setImage(Image img) {
		
		 DetailrecipeImage.setImage(img);
	}

	@FXML
	void backButtonClicked(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}

	public void handleButtonClick(Recipe recipe) throws SQLException {
		String MoreBtnRecipeName = recipe.getName();
    	System.out.println("------getting recipe name----" + MoreBtnRecipeName);
    	String selectQuery = "SELECT * FROM RecipesInfo WHERE recipeName = ?";
        PreparedStatement selectStatement = connectDB.prepareStatement(selectQuery);
        selectStatement.setString(1, MoreBtnRecipeName);
        ResultSet resultSet = selectStatement.executeQuery();
        System.out.println(resultSet);

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
            
            System.out.println("Inside handle button action");
          
//            DetailCardController dcl = new DetailCardController();
//        	dcl.initData(recipe);
        	
    	
            try {
        		System.out.println("Before--");
        		
               System.out.println("Inside handle button action");
                System.out.println("recipe.getImage()----"+recipe.getImageDetail(image));
//                if (image == null || image.equals(null)) {
//                    throw new Exception("Image not found");
//                }
                DetailrecipeImage.setImage(recipe.getImageDetail(image));
                
                System.out.println("after--");
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        	System.out.println("    recipeNameDetailTo -------"+recipeNameDetailTo);
            recipeNameDetailTo.setText(recipe.getName());
    		chefNameDetail.setText(recipe.getchefName());
    		recipeDescriptionDetail.setText(recipe.getDescription());
    		recipeContentsDetail.setText(recipe.getContents());
    		 if(recipe.getRating() >= 4.0) {
    			 recipeRatingDetail.setImage(new Image("File:assets/Four_star.png"));
             	return;
             }
             else {
             	
            	 recipeRatingDetail.setImage(new Image("File:assets/Three_star.jpeg"));
             	return;
             }
//        	box.setStyle("-fx-background-color:" + Color.web(colors[(int)(Math.random()*colors.length)]));
        	
    	
	}else {
        System.out.println("Recipe not found in the database");
    }
        System.out.println("Received object: " );
       
    
    
        connectDB.close();
		
	}
}
	
