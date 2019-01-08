package new_forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.AdministratorResources;
import utility.ClientResources;

public class NewAdministratorController {

	@FXML AnchorPane statusPane;
	@FXML AnchorPane menuPane;
	@FXML AnchorPane workspacePane;
	@FXML AnchorPane optionsPane;
	@FXML Button addNewUserButton;
	@FXML Button showUsersButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button helpButton;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		resources.getStage().setMaximized(true);
		resize();
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(statusPane, resources.getScreenHeight() * 0.8);
		AnchorPane.setTopAnchor(menuPane, resources.getScreenHeight() * 0.2);
		AnchorPane.setRightAnchor(menuPane, resources.getScreenWidth() * 0.8);
		AnchorPane.setTopAnchor(workspacePane, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(workspacePane, resources.getScreenWidth() * 0.2);
		AnchorPane.setRightAnchor(workspacePane, resources.getScreenWidth() * 0.1);
		AnchorPane.setTopAnchor(optionsPane, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(optionsPane, resources.getScreenWidth() * 0.9);
		addNewUserButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1);
		showUsersButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1);
		logoutButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.1);
		refreshButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.1);
		helpButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.1);
	}
	
	public void showUsers(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/new_forms/NewUserTableForm.fxml"), resources);
			if(workspacePane.getChildren().size() != 0)
				workspacePane.getChildren().remove(0);
			workspacePane.getChildren().add(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addNewUser(ActionEvent event) {
		ClientResources newResources = new ClientResources(null, resources.getClientCommunication(), resources.getUser(),
				resources.getScreenWidth() * 0.33, resources.getScreenHeight() * 0.7);
		try {
			Stage addNewUserStage = new Stage();
			addNewUserStage.setResizable(false);
			addNewUserStage.initModality(Modality.APPLICATION_MODAL);
			Parent root = FXMLLoader.load(getClass().getResource("/new_forms/NewAddNewUserForm.fxml"), newResources);
			Scene addNewUserScene = new Scene(root, newResources.getScreenWidth(), newResources.getScreenHeight());
			addNewUserStage.setScene(addNewUserScene);
			addNewUserStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logout() {}
}
