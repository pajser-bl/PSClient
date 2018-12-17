package control;

import client.ClientCommunication;
import client.User;
import exception.ConnectionTimeoutException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import utility.ClientResources;
import utility.MessageBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class LoginController {

	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Button loginButton;
	@FXML
	ClientResources resources;

	public void login(ActionEvent loginEvent) {
		try {
			resources.setClientCommunication(new ClientCommunication("192.168.0.176", 9000));
			ArrayList<String> reply = resources.getClientCommunication().login(username.getText(), password.getText());
			if (reply.get(0).equals("LOGIN OK")) {
				resources.setUser(new User(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5)));
				switch (reply.get(4)) {
				case "Operater": {
					Parent userView = FXMLLoader.load(getClass().getResource("/view/OperaterForm.fxml"), resources);
					Scene userScene = new Scene(userView);
					resources.getStage().setScene(userScene);
					break;
				}
				case "Administrator": {
					Parent userView = FXMLLoader.load(getClass().getResource("/view/AdministratorForm.fxml"),
							resources);
					Scene userScene = new Scene(userView);
					resources.getStage().setScene(userScene);
					break;
				}
				case "Supervizor": {
					Parent userView = FXMLLoader.load(getClass().getResource("/view/SupervisorForm.fxml"), resources);
					Scene userScene = new Scene(userView);
					resources.getStage().setScene(userScene);
					break;
				}
				case "TerenskiRadnik": {
					Parent userView = FXMLLoader.load(getClass().getResource("/view/FieldTechnitianForm.fxml"),
							resources);
					Scene userScene = new Scene(userView);
					resources.getStage().setScene(userScene);
					break;
				}
				}
				resources.getStage().hide();
				resources.getStage().setResizable(false);
				resources.getStage().setMaximized(true);
				resources.getStage().show();
			} else {
				resources.getClientCommunication().closeConnection();
				MessageBox.displayMessage("Greska", reply.get(1));
			}
		} catch (ConnectionTimeoutException e) {
			e.printStackTrace();
			MessageBox.displayMessage("Greska", "Veza sa serverom nije uspostavljena.");
		} catch (Exception e) {
			e.printStackTrace();
			resources.getClientCommunication().closeConnection();
		}
	}
}
