package controller.administrator;

import client.User;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import utility.UsersListResources;
import utility.MessageBox;
import utility.Parser;

public class AddNewUserController {

	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField password;
	@FXML TextField licence;
	@FXML TextField userName;
	@FXML AnchorPane bottomPane;
	@FXML AnchorPane accountSettingsPane;
	@FXML AnchorPane accountSettingsLabels;
	@FXML AnchorPane accountSettingsInput;
	@FXML AnchorPane userDataInput;
	@FXML AnchorPane userDataLabels;
	@FXML AnchorPane topPane;
	@FXML DatePicker date;
	@FXML ComboBox<String> userType;
	@FXML ComboBox<String> qualification;
	@FXML Button createNewUser;
	@FXML UsersListResources resources;

	@FXML public void initialize() {
		resize();
	}
	
	public void createNewUser(ActionEvent event) {
		try {
			ArrayList<String> reply = resources.getClientCommunication().newUser(name.getText(), lastName.getText(), date.getValue().toString(),
				(String) userType.getValue(), qualification.getValue() + (licence.getText().isEmpty() ? "" : ", " +
				licence.getText()), userName.getText(), password.getText());
			if (reply.get(0).equals("ADD USER OK")) {
				MessageBox.displayMessage("Potvrda", "Korisnik uspjesno kreiran");
				if(!resources.getUsers().isEmpty())
					resources.getUsers().add(new User(reply.get(1), name.getText(), lastName.getText(), userType.getValue(),
							userName.getText()));
				resources.getStage().close();
			} else
				MessageBox.displayMessage("Greska", reply.get(1));
		} catch (Exception e) {
			MessageBox.displayMessage("Greska", e.toString());
			e.printStackTrace();
		}
	}
	
	public void resize() {
		AnchorPane.setTopAnchor(bottomPane, resources.getScreenHeight() * 0.6);
		AnchorPane.setBottomAnchor(topPane, resources.getScreenHeight() * 0.4);
		AnchorPane.setTopAnchor(userDataLabels, 0.0);
		AnchorPane.setRightAnchor(userDataLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(userDataInput, 0.0);
		AnchorPane.setLeftAnchor(userDataInput, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(accountSettingsPane, 0.5);
		AnchorPane.setBottomAnchor(accountSettingsPane, 0.1);
		AnchorPane.setTopAnchor(accountSettingsLabels, resources.getScreenHeight() * 0.0125);
		AnchorPane.setRightAnchor(accountSettingsLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(accountSettingsInput, resources.getScreenHeight() * 0.0125);
		AnchorPane.setLeftAnchor(accountSettingsInput, resources.getScreenWidth() * 0.5);
	}
}
