package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfilController {

	@FXML private Label name;
	@FXML private Label lastName;
	@FXML private Label username;
	@FXML private Label qualification;
	@FXML private Label type;

	public void showProfile(String nameInput, String lastNameInput, String usernameInput, String qulaificationInput, String typeInput) {
		name.setText(nameInput);
		lastName.setText(lastNameInput);
		username.setText(usernameInput);
		qualification.setText(qulaificationInput);
		type.setText(typeInput);
	}
}
