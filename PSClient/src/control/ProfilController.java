package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ProfilController implements Initializable {

	@FXML private Label name;
	@FXML private Label lastName;
	@FXML private Label username;
	@FXML private Label qualification;
	@FXML private Label type;

	public void initialize(URL location, ResourceBundle resources) {
		/*name = new Label();
		lastName = new Label();
		username = new Label();
		qualification = new Label();
		type = new Label();*/
	}
	
	public void showProfile(String nameInput, String lastNameInput, String usernameInput, String qulaificationInput, String typeInput) {
		name.setText(nameInput);
		lastName.setText(lastNameInput);
		username.setText(usernameInput);
		qualification.setText(qulaificationInput);
		type.setText(typeInput);
	}
}
