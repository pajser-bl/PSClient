package new_forms;

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
	
	public void showActiveUsers(ActionEvent event) {}
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
