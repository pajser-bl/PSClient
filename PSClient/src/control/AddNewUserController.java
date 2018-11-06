package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class AddNewUserController {

	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField password;
	@FXML TextField driversLicence;
	@FXML Button createNewUser;
	
	public void createNewUser(ActionEvent event) {
		
	}
}
