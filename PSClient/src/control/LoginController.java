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
import java.util.ArrayList;

public class LoginController implements Initializable {

		public TextField username;
		public PasswordField password;
		
		public void initialize(URL location, ResourceBundle resource) {
			Client.clientCommunication = new ClientCommunication("192.168.89.74", 9000);
		}
		
		public void login(ActionEvent loginEvent) throws Exception {
			Parent userView = FXMLLoader.load(getClass().getResource("/view/AdministratorForm.fxml"));
			System.out.println("asdasdasdasd");
			Scene userScene = new Scene(userView);
			ArrayList<String> reply = RequestFunctionality.login(Client.clientCommunication, username.getText(), password.getText());
			System.out.println(reply.get(0));
			if(reply.get(0).equals("LOGIN OK")) {
				System.out.println(reply.get(0));
				Node source = (Node) loginEvent.getSource();
				Stage mainStage = (Stage) source.getScene().getWindow();
				mainStage.hide();
				mainStage.setScene(userScene);
				mainStage.show();
			}
		}
}
