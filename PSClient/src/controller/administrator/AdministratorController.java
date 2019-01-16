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

	@FXML AnchorPane statusAnchor;
	@FXML AnchorPane menuAnchor;
	@FXML AnchorPane workspaceAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML Button addNewUserButton;
	@FXML Button showUsersButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button helpButton;
	@FXML ClientResources resources;
	private ArrayList<User> users = new ArrayList<User>();

	@FXML public void initialize() {
		resize();
		//name.setText("Ime: " + resources.getUser().getName());
		//lastName.setText("Prezime: " + resources.getUser().getLastName());
		resources.getStage().setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public void addNewUser(ActionEvent event) {
		AdministratorResources adminResources = new AdministratorResources(resources, users);
		adminResources.setScreenHeight(resources.getScreenHeight() * 0.7);
		adminResources.setScreenWidth(resources.getScreenWidth() * 0.5);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/administrator/AddNewUserForm.fxml"), adminResources);
			Stage addNewUserStage = new Stage();
			adminResources.setStage(addNewUserStage);
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
			Parent root = FXMLLoader.load(getClass().getResource("/view/administrator/UserTableForm.fxml"), adminResources);
			if(workspaceAnchor.getChildren().size() != 0)
				workspaceAnchor.getChildren().remove(0);
			workspaceAnchor.getChildren().add(root);
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
	
	public void resize() {
		AnchorPane.setBottomAnchor(statusAnchor, resources.getScreenHeight() * 0.8);
		AnchorPane.setTopAnchor(menuAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setRightAnchor(menuAnchor, resources.getScreenWidth() * 0.8);
		AnchorPane.setTopAnchor(workspaceAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(workspaceAnchor, resources.getScreenWidth() * 0.2);
		AnchorPane.setRightAnchor(workspaceAnchor, resources.getScreenWidth() * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(optionsAnchor, resources.getScreenWidth() * 0.9);
		addNewUserButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		showUsersButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		logoutButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		refreshButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		helpButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
	}
}
