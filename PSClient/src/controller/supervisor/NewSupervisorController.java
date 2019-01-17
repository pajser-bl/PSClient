package controller.supervisor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utility.ClientResources;

public class NewSupervisorController {

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
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		resources.getStage().setMaximized(true);
		resize();
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
	public void logout() {}
	
	public void resize() {
		AnchorPane.setBottomAnchor(statusAnchor, resources.getScreenHeight() * 0.8);
		AnchorPane.setTopAnchor(menuAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setRightAnchor(menuAnchor, resources.getScreenWidth() * 0.8);
		AnchorPane.setTopAnchor(workspaceAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(workspaceAnchor, resources.getScreenWidth() * 0.2);
		AnchorPane.setRightAnchor(workspaceAnchor, resources.getScreenWidth() * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(optionsAnchor, resources.getScreenWidth() * 0.9);
		activeUsersButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		clientsButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		sessionsButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		interventionsButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		logoutButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		refreshButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		helpButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
	}
}
