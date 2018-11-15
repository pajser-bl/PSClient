package control;

import client.Client;
import client.ClientCommunication;
import client.User;
import client.RequestFunctionality;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.MessageBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class LoginController{

		@FXML TextField username;
		@FXML PasswordField password;
		@FXML Button loginButton;
		
		public void login(ActionEvent loginEvent) {
			try {
				Client.clientCommunication = new ClientCommunication("127.0.0.1", 9000);
				ArrayList<String> reply = RequestFunctionality.login(Client.clientCommunication, username.getText(), password.getText());
				if(reply.get(0).equals("LOGIN OK")) {
					Parent userView = FXMLLoader.load(getClass().getResource("/view/AdministratorForm.fxml"));
					Client.user = new User(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5));
					Scene userScene = new Scene(userView);
					Node source = (Node) loginEvent.getSource();
					Stage mainStage = (Stage) source.getScene().getWindow();
					mainStage.hide();
					mainStage.setScene(userScene);
					mainStage.setResizable(false);
					mainStage.setMaximized(true);
					mainStage.show();
				} else {
					Client.clientCommunication.getSocket().close();
					MessageBox.displayMessage("Greska", reply.get(0));
				}
			} catch(Exception e) {
				MessageBox.displayMessage("Greska", "Veza sa serverom nije uspostavljena");
			}
		}
		
		public void logout() {
			
		}
}
