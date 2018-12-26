package controller.administrator;

import client.User;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.AdministratorResources;
import utility.ChoiceBox;
import utility.ClientResources;
import utility.MessageBox;

public class AdministratorController {

	@FXML AnchorPane workSpace;
	@FXML ClientResources resources;
	@FXML Button addNewUserButton;
	@FXML Button helpButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button showUsers;
	@FXML Label name;
	@FXML Label lastName;
	private ArrayList<User> users = new ArrayList<User>();

	@FXML public void initialize() {
		name.setText("Ime: " + resources.getUser().getName());
		lastName.setText("Prezime: " + resources.getUser().getLastName());
		resources.getStage().setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public void addNewUser(ActionEvent event) {
		AdministratorResources adminResources = new AdministratorResources(resources, users);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/administrator/AddNewUserForm.fxml"), adminResources);
			Stage addNewUserStage = new Stage();
			addNewUserStage.setScene(new Scene(root));
			addNewUserStage.initModality(Modality.APPLICATION_MODAL);
			addNewUserStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showUsers(ActionEvent event) {
		if(users.isEmpty()) {
			ArrayList<String> reply = resources.getClientCommunication().getUsers(resources.getUser().getUserId());
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
			AdministratorResources adminResources = new AdministratorResources(resources, users);
			Parent root = FXMLLoader.load(getClass().getResource("/view/administrator/NewUserTableForm.fxml"), adminResources);
			if(workSpace.getChildren().size() != 0)
				workSpace.getChildren().remove(0);
			workSpace.getChildren().add(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout(ActionEvent event) {
		close();
	}
	
	public void close() {
		boolean answer = ChoiceBox.displayChoice("Odjava", "Da li ste sigurni da zelite da se odjavite?");
		if(answer) {
			resources.getClientCommunication().logout(resources.getUser().getUserId());
			resources.getClientCommunication().closeConnection();
			resources.getStage().hide();
		}
	}
}
