package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class Register {
	@FXML protected TextField nameRegister;
    @FXML protected TextField passwordRegister;
    @FXML protected TextField emailRegister;
    @FXML protected TextField phoneRegister;
    @FXML protected TextField confirmPasswordRegister;
   

    protected abstract void register();

    
}
