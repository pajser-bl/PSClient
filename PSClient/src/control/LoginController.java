package control;

import client.Client;
import client.ClientCommunication;
import client.RequestFunctionality;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class LoginController implements Initializable {

		public TextField username;
		private PasswordField password;
		private Button loginButton;
		
		public void initialize(URL location, ResourceBundle resource) {
			try {
				Client.clientCommunication = new ClientCommunication("192.168.89.74", 9000);
			} catch(Exception e) {
				System.out.println("Veza sa serverom nije uspostavljena");
			}
		}
		
		public void login(ActionEvent loginEvent) throws Exception {
			Parent userView = FXMLLoader.load(getClass().getResource("/view/AdministratorForm.fxml"));
			Scene userScene = new Scene(userView);
			ArrayList<String> reply = RequestFunctionality.login(Client.clientCommunication, username.getText(), password.getText());
			if(reply.get(0).equals("LOGIN OK")) {
				Node source = (Node) loginEvent.getSource();
				Stage mainStage = (Stage) source.getScene().getWindow();
				mainStage.hide();
				mainStage.setScene(userScene);
				mainStage.show();
			}
		}
}
