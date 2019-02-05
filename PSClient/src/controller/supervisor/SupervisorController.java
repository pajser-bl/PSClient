package controller.supervisor;

import client.ClientCommunication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import utility.ChoiceBox;

public class SupervisorController {

	private ClientCommunication clientComm;
	private User user;
	private Stage mainStage;
	private double screenHeight;
	private double screenWidth;
	@FXML AnchorPane avatarAnchor;
	@FXML AnchorPane menuAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML AnchorPane statusAnchor;
	@FXML AnchorPane workspaceAnchor;
	@FXML Button activeUsersButton;
	@FXML Button clientsButton;
	@FXML Button sessionsButton;
	@FXML Button interventionsButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button helpButton;
	@FXML Label name;
	@FXML Label lastName;
	@FXML ImageView avatar;
	@FXML VBox userData;
	
	@FXML public void initialize() {
		resize();
		name.setText(user.getName());
		lastName.setText(user.getLastName());
		mainStage.setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public SupervisorController(Stage mainStage, ClientCommunication clientComm, User user, double screenWidth, double screenHeight) {
		this.mainStage = mainStage;
		this.clientComm = clientComm;
		this.user = user;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public void showActiveUsers(ActionEvent event) {
		/*ArrayList<String> reply = RequestFunctionality.viewActiveUsers(Client.clientCommunication);
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
		}*/
	}
	
	public void showClients(ActionEvent event) {}
	public void showSessions(ActionEvent event) {}
	public void showInterventions(ActionEvent event) {}
	
	public void logout() {
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
		AnchorPane.setBottomAnchor(statusAnchor, screenHeight * 0.7715);
		AnchorPane.setTopAnchor(menuAnchor, screenHeight * 0.2);
		AnchorPane.setRightAnchor(menuAnchor, screenWidth * 0.8);
		AnchorPane.setTopAnchor(workspaceAnchor, screenHeight * 0.2);
		AnchorPane.setLeftAnchor(workspaceAnchor, screenWidth * 0.2);
		AnchorPane.setRightAnchor(workspaceAnchor, screenWidth * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.2);
		AnchorPane.setLeftAnchor(optionsAnchor, screenWidth * 0.9);
		AnchorPane.setRightAnchor(avatarAnchor, screenWidth * 0.9);
		AnchorPane.setLeftAnchor(userData, screenWidth * 0.1);
		AnchorPane.setRightAnchor(userData, screenWidth * 0.8);
		avatar.setFitHeight(screenHeight * 0.8);
		avatar.setFitWidth(screenWidth * 0.1);
		activeUsersButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		clientsButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		sessionsButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		interventionsButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		logoutButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
		refreshButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
		helpButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
	}
}
