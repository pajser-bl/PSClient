package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfilController{

	@FXML static Label name;
	@FXML static Label lastName;
	@FXML static Label username;
	@FXML static Label qualification;
	@FXML static Label type;
	
	public static void showProfile(String nameInput, String lastNameInput, String usernameInput, String qulaificationInput, String typeInput) {
		System.out.println(name.getText());
		System.out.println(nameInput);
		name.setText(nameInput);
		lastName.setText(lastNameInput);
		username.setText(usernameInput);
		qualification.setText(qulaificationInput);
		type.setText(typeInput);
	}
}
