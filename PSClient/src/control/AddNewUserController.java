package control;

import client.Client;
import client.RequestFunctionality;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import utility.ErrorBox;
import utility.Parser;

public class AddNewUserController {

	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField password;
	@FXML TextField licence;
	@FXML TextField dateOfBirth;
	@FXML TextField userName;
	@FXML ComboBox userType;
	@FXML ComboBox qualification;
	@FXML Button createNewUser;

	public void createNewUser(ActionEvent event) {
		try {
			String parsedDate = Parser.parseNewUser(name.getText(), lastName.getText(), dateOfBirth.getText(),
													licence.getText(), userName.getText(), password.getText());
			ArrayList<String> response = RequestFunctionality.newUser(Client.clientCommunication, name.getText(), lastName.getText(), parsedDate,
																	  (String) userType.getValue(), qualification.getValue() + ", "
																	  + licence.getText());
		} catch(Exception e) {
			ErrorBox.displayError(e.toString());
			e.printStackTrace();
		}
	}
}
