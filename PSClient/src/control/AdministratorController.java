package control;

import client.Client;
import client.RequestFunctionality;
import client.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.ChoiceBox;
import utility.MessageBox;

public class AdministratorController implements Initializable{

	@FXML Button addNewUserButton;
	@FXML Button showUsers;
	@FXML Button logoutButton;
	@FXML Label name;
	@FXML Label lastName;
	@FXML AnchorPane anchor;
	private static ArrayList<User> users = new ArrayList<User>();

	public void initialize(URL arg0, ResourceBundle arg1) {
		name.setText("Ime:" + Client.user.getName());
		lastName.setText("Prezime: " + Client.user.getLastName());
	}
	
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
			if(reply.get(0).equals("VIEW USERS NOT OK"))
				MessageBox.displayMessage("Greska", "Greska pri preuzimanju liste korisnika");
			else {
				for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
					String[] userData = reply.get(i + 2).split(":");
					users.add(new User(userData[0], userData[1], userData[2], userData[3], userData[4]));
				}
			}
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
	
	public void logout(ActionEvent logout) {
		Node source = (Node) logout.getSource();
		Stage mainStage = (Stage) source.getScene().getWindow();
		Client.logout(mainStage);
	}
	
	public static ObservableList<User> getUsers() {
		return FXCollections.observableArrayList(users);
	}
}
