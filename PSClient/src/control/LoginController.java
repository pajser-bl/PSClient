package control;

import client.Client;
import client.ClientCommunication;
import client.Request;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import java.util.ArrayList;

public class LoginController implements Initializable{

		public TextField username;
		public PasswordField password;
		
		public void initialize(URL location, ResourceBundle resource) {
			Client.clientCommunication = new ClientCommunication("192.168.1.2", 9000);
		}
		
		public void login(ActionEvent loginEvent) {
			String requestType = "LOGIN";
			ArrayList<String> reply = new ArrayList<String>();
			ArrayList<String> requestArray = new ArrayList<String>();
			requestArray.add(username.getText());
			requestArray.add(password.getText());
			Request request = new Request(requestType, requestArray);
			reply = Client.clientCommunication.sendRequest(request);
			System.out.println(reply);
		}
}
