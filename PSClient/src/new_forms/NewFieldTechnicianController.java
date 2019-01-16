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
import utility.ClientResources;

public class NewFieldTechnicianController {

	@FXML AnchorPane statusPane;
	@FXML AnchorPane menuPane;
	@FXML AnchorPane optionsPane;
	@FXML AnchorPane workspacePane;
	@FXML Button stateButton;
	@FXML Button mapButton;
	@FXML Button reportButton;
	@FXML Button sessionButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button helpButton;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		resources.getStage().setMaximized(true);
		resize();
	}
	
	public void changeState(ActionEvent event) {}
	
	public void showMap(ActionEvent event) {
		ClientResources reportResources = new ClientResources(null, resources.getClientCommunication(), resources.getUser(),
				resources.getScreenWidth() * 0.33, resources.getScreenHeight() * 0.7);
		try {
			Stage addNewUserStage = new Stage();
			addNewUserStage.setResizable(false);
			addNewUserStage.initModality(Modality.APPLICATION_MODAL);
			Parent root = FXMLLoader.load(getClass().getResource("/new_forms/NewRoadReportForm.fxml"), reportResources);
			Scene addNewUserScene = new Scene(root, reportResources.getScreenWidth(), reportResources.getScreenHeight());
			addNewUserStage.setScene(addNewUserScene);
			addNewUserStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void report(ActionEvent event) {}
	public void showSession(ActionEvent event) {}
	public void logout(ActionEvent event) {}
	
	public void resize() {
		AnchorPane.setBottomAnchor(statusPane, resources.getScreenHeight() * 0.8);
		AnchorPane.setTopAnchor(menuPane, resources.getScreenHeight() * 0.2);
		AnchorPane.setRightAnchor(menuPane, resources.getScreenWidth() * 0.8);
		AnchorPane.setTopAnchor(workspacePane, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(workspacePane, resources.getScreenWidth() * 0.2);
		AnchorPane.setRightAnchor(workspacePane, resources.getScreenWidth() * 0.1);
		AnchorPane.setTopAnchor(optionsPane, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(optionsPane, resources.getScreenWidth() * 0.9);
		stateButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		mapButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		reportButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		sessionButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		logoutButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		refreshButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		helpButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
	}
}