package controller.administrator;

import client.ClientCommunication;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import utility.ChoiceBox;
import utility.MessageBox;

public class AdministratorController {

	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	private ObservableList<User> users;
	private Stage mainStage;
	private User user;
	@FXML AnchorPane avatarAnchor;
	@FXML AnchorPane statusAnchor;
	@FXML AnchorPane menuAnchor;
	@FXML AnchorPane workspaceAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML Button addNewUserButton;
	@FXML Button showUsersButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button helpButton;
	@FXML HBox profileHBox;
	@FXML ImageView avatar;
	@FXML Label name;
	@FXML Label lastName;
	@FXML VBox userData;

	@FXML public void initialize() {
		resize();
		name.setText("  " + user.getName());
		lastName.setText("  " + user.getLastName());
		mainStage.setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public AdministratorController(Stage mainStage, ClientCommunication clientComm, User user, double screenWidth, double screenHeight) {
		this.mainStage = mainStage;
		this.clientComm = clientComm;
		this.user = user;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		users = FXCollections.observableArrayList(new ArrayList<User>());
	}
	
	public void addNewUser(ActionEvent event) {
		Stage addNewUserStage = new Stage();
		if(users.isEmpty()) {
			ArrayList<String> reply = clientComm.getUsers(user.getUserId());
			if(reply.get(0).equals("VIEW USERS NOT OK"))
				MessageBox.displayMessage("Greska", "Greska pri preuzimanju liste korisnika");
			else for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
					String[] userData = reply.get(i + 2).split(":");
					users.add(new User(userData[0], userData[1], userData[2], userData[3], userData[4],null, null, null));
				}
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/administrator/AddNewUserForm.fxml"));
			loader.setControllerFactory(e -> new AddNewUserController(addNewUserStage, clientComm, users, false, screenWidth * 0.3,
					screenHeight * 0.75));
			Parent addNewUserView = loader.load();
			addNewUserStage.setScene(new Scene(addNewUserView, screenWidth * 0.3, screenHeight * 0.75));
			addNewUserStage.initModality(Modality.APPLICATION_MODAL);
			addNewUserStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showUsers(ActionEvent event) {
		if(users.isEmpty()) {
			ArrayList<String> reply = clientComm.getUsers(user.getUserId());
			if(reply.get(0).equals("VIEW USERS NOT OK"))
				MessageBox.displayMessage("Greska", "Greska pri preuzimanju liste korisnika");
			else {
				for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
					String[] userData = reply.get(i + 2).split(":");
					users.add(new User(userData[0], userData[1], userData[2], userData[3], userData[4],null, null, null));
				}
			}
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/administrator/UserTableForm.fxml"));
			loader.setControllerFactory(e -> new UserTableController(mainStage, clientComm, users, screenWidth,
					screenHeight));
			Parent userTableView = loader.load();
			if(workspaceAnchor.getChildren().size() != 0)
				workspaceAnchor.getChildren().remove(0);
			workspaceAnchor.getChildren().add(userTableView);
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
			clientComm.logout(user.getUserId());
			clientComm.closeConnection();
			mainStage.hide();
		}
	}
	
	public void resize() {
		//top panel
		AnchorPane.setBottomAnchor(statusAnchor, screenHeight * 0.7715);
		
		//profile picture
		AnchorPane.setRightAnchor(avatarAnchor, screenWidth * 0.9);
		AnchorPane.setLeftAnchor(userData, screenWidth * 0.1);
		AnchorPane.setRightAnchor(userData, screenWidth * 0.8);
		avatar.setFitHeight(screenHeight * 0.2);
		avatar.setFitWidth(screenWidth * 0.1);
		
		//left panel
		AnchorPane.setTopAnchor(menuAnchor, screenHeight * 0.2);
		AnchorPane.setRightAnchor(menuAnchor, screenWidth * 0.8);
		
		//center panel
		AnchorPane.setTopAnchor(workspaceAnchor, screenHeight * 0.2);
		AnchorPane.setLeftAnchor(workspaceAnchor, screenWidth * 0.2);
		AnchorPane.setRightAnchor(workspaceAnchor, screenWidth * 0.1);
		
		//right panel
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.2);
		AnchorPane.setLeftAnchor(optionsAnchor, screenWidth * 0.9);
		
		//buttons
		addNewUserButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		showUsersButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		logoutButton.setPrefSize(screenWidth * 0.095, screenHeight * 0.14);
		refreshButton.setPrefSize(screenWidth * 0.095, screenHeight * 0.14);
		helpButton.setPrefSize(screenWidth * 0.095, screenHeight * 0.14);
	}
}
