package new_forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
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
	public void showMap(ActionEvent event) {}
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
