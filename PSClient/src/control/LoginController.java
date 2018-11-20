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
				Client.clientCommunication = new ClientCommunication("192.168.0.70", 9000);
				ArrayList<String> reply = RequestFunctionality.login(Client.clientCommunication, username.getText(), password.getText());
				if(reply.get(0).equals("LOGIN OK")) {
					Client.user = new User(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5));
					Node source = (Node) loginEvent.getSource();
					Stage mainStage = (Stage) source.getScene().getWindow();
					if(reply.get(4).equals("Operater")) {
						System.out.println("1111111");
						Parent userView = FXMLLoader.load(getClass().getResource("/view/OperatorForm.fxml"));
						Scene userScene = new Scene(userView);
						mainStage.setScene(userScene);
						System.out.println("2222222");
					}
					else {
						Parent userView = FXMLLoader.load(getClass().getResource("/view/AdministratorForm.fxml"));
						Scene userScene = new Scene(userView);
						mainStage.setScene(userScene);
					}
					mainStage.hide();
					mainStage.setResizable(false);
					mainStage.setMaximized(true);
					mainStage.show();
				} else {
					Client.clientCommunication.getSocket().close();
					MessageBox.displayMessage("Greska", reply.get(1));
				}
			} catch(Exception e) {
				MessageBox.displayMessage("Greska", "Veza sa serverom nije uspostavljena");
			}
		}
}
