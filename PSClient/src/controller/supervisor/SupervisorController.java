package controller.supervisor;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.Client;
import client.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.MessageBox;

/*public class SupervisorController implements Initializable {
	@FXML
	Label name;
	@FXML Label lastName;
	@FXML Button logoutButton;
	@FXML Button showUsers;
	@FXML AnchorPane anchor;
	private static ArrayList<User> activeUsers = new ArrayList<User>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setText("Ime:" + Client.user.getName());
		lastName.setText("Prezime: " + Client.user.getLastName());
	}

	public void showActiveUsers(ActionEvent event) {
		//OVO NE RADI SREDI!
		ArrayList<String> reply = RequestFunctionality.viewActiveUsers(Client.clientCommunication);
		System.out.println(reply);
		if (reply.get(0).equals("VIEW ONLINE USERS OK")) {
			for (int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] userData = reply.get(i+2).split(":");
				activeUsers.add(new User(userData[0], userData[1], userData[2], userData[3],userData[4]));
			}
		} else {
			MessageBox.displayMessage("Greska", "Greska pri preuzimanju liste korisnika");
		}
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/SupervisorActiveUsersForm.fxml"));
			if (anchor.getChildren().size() != 0)
				anchor.getChildren().remove(0);
			anchor.getChildren().add(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout(ActionEvent logout) {
		Node source = (Node) logout.getSource();
		Stage mainStage = (Stage) source.getScene().getWindow();
		Client.logout(mainStage);
	}
}*/
