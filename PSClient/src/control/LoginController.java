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
import java.io.IOException;
import java.util.ArrayList;

public class LoginController{

		@FXML TextField username;
		@FXML PasswordField password;
		@FXML Button loginButton;
		
		public void login(ActionEvent loginEvent) {
			Node source = (Node) loginEvent.getSource();
			Stage mainStage = (Stage) source.getScene().getWindow();
			try {
				Client.clientCommunication = new ClientCommunication("127.0.0.1", 9000);
				ArrayList<String> reply = RequestFunctionality.login(Client.clientCommunication, username.getText(), password.getText());
				for(int i = 0; i < reply.size(); i++)
					System.out.println(reply.get(i));
				if(reply.get(0).equals("LOGIN OK")) {
					Client.user = new User(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5));
					if(reply.get(4).equals("Operater")) {
						Parent userView = FXMLLoader.load(getClass().getResource("/view/OperatorForm.fxml"));
						Scene userScene = new Scene(userView);
						mainStage.setScene(userScene);
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
					MessageBox.displayMessage("Greska", reply.get(1));
					Client.logout(mainStage);
				}
			} catch(IOException e) {
				e.printStackTrace();
				MessageBox.displayMessage("Greska", "Loadanje FXML-a nije uspjelo");
				Client.logout(mainStage);
			} catch(Exception e) {
				e.printStackTrace();
				MessageBox.displayMessage("Greska", "Veza sa serverom nije uspostavljena");
				Client.logout(mainStage);
			}
		}
}
