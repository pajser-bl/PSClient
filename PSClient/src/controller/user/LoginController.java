package controller.user;

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

public class LoginController{

		@FXML TextField username;
		@FXML PasswordField password;
		@FXML Button loginButton;
		@FXML ClientResources resources;
		
		public void login(ActionEvent loginEvent) {
			try {
<<<<<<< HEAD
				resources.setClientCommunication(new ClientCommunication("127.0.0.1", 9000));
=======
				resources.setClientCommunication(new ClientCommunication("192.168.0.48", 9000));
>>>>>>> branch 'master' of https://github.com/pajser-bl/PSClient
				ArrayList<String> reply = resources.getClientCommunication().login(username.getText(), password.getText());
				if(reply.get(0).equals("LOGIN OK")) {
					resources.setUser(new User(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5)));
					if(reply.get(4).equals("Operater")) {
						Parent userView = FXMLLoader.load(getClass().getResource("/view/operater/OperaterForm.fxml"), resources);
						Scene userScene = new Scene(userView);
						resources.getStage().setScene(userScene);
					} else if(reply.get(4).equals("Terenski Radnik")) {
						Parent userView = FXMLLoader.load(getClass().getResource("/view/field_technician/FieldTechnicianForm.fxml"),
								resources);
						Scene userScene = new Scene(userView);
						resources.getStage().setScene(userScene);
					} else {
						Parent userView = FXMLLoader.load(getClass().getResource("/view/administrator/AdministratorForm.fxml"), 
								resources);
						Scene userScene = new Scene(userView);
						resources.getStage().setScene(userScene);
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
