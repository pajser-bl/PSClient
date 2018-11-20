package control;

import client.Client;
import client.User;
import client.RequestFunctionality;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import utility.MessageBox;
import utility.Parser;

public class AddNewUserController {

	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField password;
	@FXML TextField licence;
	@FXML TextField dateOfBirth;
	@FXML TextField userName;
	@FXML ComboBox<String> userType;
	@FXML ComboBox<String> qualification;
	@FXML Button createNewUser;

	public void createNewUser(ActionEvent event) {
		try {
			String parsedDate = Parser.parseNewUser(name.getText(), lastName.getText(), dateOfBirth.getText(), licence.getText(),
													userName.getText(), password.getText());
			ArrayList<String> reply = RequestFunctionality.newUser(Client.clientCommunication, name.getText(), lastName.getText(),
																   parsedDate, (String) userType.getValue(), qualification.getValue()
																   + (licence.getText().isEmpty() ? "" : ", " + licence.getText()) ,
																   userName.getText(), password.getText());
			if(reply.get(0).equals("ADD USER OK")) {
				MessageBox.displayMessage("Potvrda", "Korisnik uspjesno kreiran");
				AdministratorController.getUsers().add(new User(reply.get(1), name.getText(), lastName.getText(), userType.getValue(),
													   userName.getText()));
				userName.clear(); lastName.clear(); password.clear(); licence.clear(); dateOfBirth.clear(); userName.clear();
			} else MessageBox.displayMessage("Greska", reply.get(1));
		} catch(Exception e) {
			MessageBox.displayMessage("Greska", e.toString());
			e.printStackTrace();
		}
	}
}
