package application;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class CreatePlanMeal {
	@FXML
    private TextArea IngredientsRecipe;

    @FXML
    private TextArea InstructionsRecipe;

    @FXML
    private Button backbtn;

    @FXML
    private ImageView recipeImage;

    @FXML
    private TextField prepTime;

    @FXML
    private Button submit;

    @FXML
    private Button uploadPicture;
    
    @FXML
    private TextField recipeName;


    
    MealPlan mealPlan = new MealPlan();
    
    @FXML 
    public void uploadPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
           
            Image image = new Image(selectedFile.toURI().toString());
            recipeImage .setImage(image); 
            // do something with the imageView...
        }
    }
    
    @FXML
    public void submitBtnAction(ActionEvent event) {
        String Name = recipeName.getText();
        String Ingredients = IngredientsRecipe.getText();
        String Instructions = InstructionsRecipe.getText();
        String Time = prepTime.getText();

       

        Image image = recipeImage.getImage();
        byte[] imageData = null;
        if (image != null) {
            PixelReader pixelReader = image.getPixelReader();
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            WritablePixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteBgraInstance();
            ByteBuffer byteBuffer = ByteBuffer.allocate(width * height * 4);
            pixelReader.getPixels(0, 0, width, height, pixelFormat, byteBuffer, width * 4);
            imageData = byteBuffer.array();
        }



       

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        int userId = UserAccount.idUserAccount;

        try {
            PreparedStatement Insertps = connectDB.prepareStatement("INSERT INTO CustomMealPlan (idUserAccount, recipeName, recipeContents, recipeDescription, recipeImage, prepTime) VALUES (?, ?, ?, ?, ?, ?)");
            Insertps.setInt(1, userId);
            Insertps.setString(2, Name);
            Insertps.setString(3, Ingredients);
            Insertps.setString(4, Instructions);
            Insertps.setBytes(5, imageData);
            Insertps.setString(6, Time);
            Insertps.executeUpdate();
            
            connectDB.close();
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create new meal plan");
        alert.setHeaderText(null);
        alert.setContentText("Added recipe to custom meal plan");
        alert.showAndWait();
    }
    
    @FXML
    public void backBtnAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MealPlan.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
    


}
