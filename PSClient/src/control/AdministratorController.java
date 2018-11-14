package control;

import client.Client;
import client.RequestFunctionality;
import client.User;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.ChoiceBox;
import utility.MessageBox;

public class AdministratorController {

	@FXML Button addNewUserButton;
	@FXML Button showUsers;
	@FXML Button logoutButton;
	@FXML AnchorPane anchor;
	private ArrayList<User> users = new ArrayList<User>();
	
	public void addNewUser(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewUserForm.fxml"));
			if(anchor.getChildren().size() != 0)
				anchor.getChildren().remove(0);
			anchor.getChildren().add(root);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showUsers(ActionEvent event) {
		if(users.isEmpty()) {
			ArrayList<String> reply = RequestFunctionality.getUsers(Client.clientCommunication, Client.user.getUserId());
			createUsers(reply);
			for(int i = 2; i < Integer.parseInt(reply.get(1)); i++) {
				String[] userData = reply.get(i).split(":");
				users.add(new User(userData[0], userData[1], userData[2], userData[3]));
			}
			for(int i = 0; i < users.size(); i++)
				System.out.println(users.get(i).toString());
		}
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/UserTableForm.fxml"));
			if(anchor.getChildren().size() != 0)
				anchor.getChildren().remove(0);
			anchor.getChildren().add(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUsers(ArrayList<String> serverReply) {
		
	}
	
	public void logout(ActionEvent logout) {
		if(ChoiceBox.displayChoice("Odjava", "Da li ste sigurni da zelite da se odjavite?") == true)
			try {
				RequestFunctionality.logout(Client.clientCommunication, Client.user.getUserId());
				Client.clientCommunication.getSocket().close();
				Node source = (Node) logout.getSource();
				Stage mainStage = (Stage) source.getScene().getWindow();
				mainStage.close();
			} catch(Exception e) {
				MessageBox.displayMessage("Greska", "Greska kod logouta");
			}
	}
}
