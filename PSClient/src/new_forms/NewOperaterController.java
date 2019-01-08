package new_forms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utility.ClientResources;

public class NewOperaterController {

	@FXML AnchorPane statusPane;
	@FXML AnchorPane menuPane;
	@FXML AnchorPane workspacePane;
	@FXML AnchorPane optionsPane;
	@FXML Button interventionsButton;
	@FXML Button mapButton;
	@FXML Button sessionButton;
	@FXML Button vehiclesButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button helpButton;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		resources.getStage().setMaximized(true);
		resize();
	}
	
	public void showInterventions() {}
	public void showMap() {}
	public void showSession() {}
	public void showVehicles() {}
	public void logout() {}
	
	public void resize() {
		AnchorPane.setBottomAnchor(statusPane, resources.getScreenHeight() * 0.8);
		AnchorPane.setTopAnchor(menuPane, resources.getScreenHeight() * 0.2);
		AnchorPane.setRightAnchor(menuPane, resources.getScreenWidth() * 0.8);
		AnchorPane.setTopAnchor(workspacePane, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(workspacePane, resources.getScreenWidth() * 0.2);
		AnchorPane.setRightAnchor(workspacePane, resources.getScreenWidth() * 0.1);
		AnchorPane.setTopAnchor(optionsPane, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(optionsPane, resources.getScreenWidth() * 0.9);
		interventionsButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1);
		mapButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1);
		sessionButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1);
		vehiclesButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1);
		logoutButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.1);
		refreshButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.1);
		helpButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.1);
	}
}
