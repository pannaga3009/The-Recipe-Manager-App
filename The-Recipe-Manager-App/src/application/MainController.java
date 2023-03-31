package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {
	@FXML
    private Label label;
    
    @FXML 
    private Circle myCircle,myCircle2, myCircle3, myCircle4, myCircle5;
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        myCircle.setStroke(Color.SEAGREEN);
        Image im1 = new Image( "C:/Users/Urmip/Downloads/The-Recipe-Manager-App/assets/download.jpeg" ,false);
        myCircle.setFill(new ImagePattern(im1));
        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
        myCircle2.setStroke(Color.SEAGREEN);
        Image im2 = new Image( "C:/Users/Urmip/Downloads/The-Recipe-Manager-App/assets/2.jpeg" ,false);
        myCircle2.setFill(new ImagePattern(im2));
        myCircle2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
        myCircle3.setStroke(Color.SEAGREEN);
        Image im3 = new Image( "C:/Users/Urmip/Downloads/The-Recipe-Manager-App/assets/3.jpeg" ,false);
        myCircle3.setFill(new ImagePattern(im3));
        myCircle3.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
        myCircle4.setStroke(Color.SEAGREEN);
        Image im4 = new Image( "C:/Users/Urmip/Downloads/The-Recipe-Manager-App/assets/4.jpeg" ,false);
        myCircle4.setFill(new ImagePattern(im4));
        myCircle4.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
        myCircle5.setStroke(Color.SEAGREEN);
        Image im5 = new Image( "C:/Users/Urmip/Downloads/The-Recipe-Manager-App/assets/5.jpeg" ,false);
        myCircle5.setFill(new ImagePattern(im5));
        myCircle5.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }    
}
