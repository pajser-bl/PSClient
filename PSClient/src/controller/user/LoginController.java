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
				resources.setClientCommunication(new ClientCommunication("192.168.1.6", 9000));
				ArrayList<String> reply = resources.getClientCommunication().login(username.getText(), password.getText());
				if(reply.get(0).equals("LOGIN OK")) {
					resources.setUser(new User(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5), null, null));
					if(reply.get(4).equals("Operater")) {
						Parent userView = FXMLLoader.load(getClass().getResource("/view/operater/OperaterForm.fxml"), resources);
						Scene userScene = new Scene(userView);
						resources.getStage().setScene(userScene);
					} else if(reply.get(4).equals("Terenski Radnik")) {
						Parent userView = FXMLLoader.load(getClass().getResource("/view/field_technician/FieldTechnicianForm.fxml"),
								resources);
						Scene userScene = new Scene(userView);
						resources.getStage().setScene(userScene);
					} else if(reply.get(4).equals("Supervizor")) {
						Parent userView = FXMLLoader.load(getClass().getResource("/view/supervisor/SupervisorForm.fxml"),
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
				MessageBox.displayMessage("Greska", "JEBEM TI ETF I SUGAVI NET!!!!!!!!");
			} catch (Exception e) {
				e.printStackTrace();
				resources.getClientCommunication().closeConnection();
			}
		}
}
