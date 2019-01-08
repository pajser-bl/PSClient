package new_forms;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import utility.ClientResources;

public class NewOpenNewInterventionController {

	@FXML AnchorPane bottomPane;
	@FXML AnchorPane topPane;
	@FXML AnchorPane clientInformationPane;
	@FXML AnchorPane clientInformationLabels;
	@FXML AnchorPane clientInformationInput;
	@FXML AnchorPane vehicleInformationPane;
	@FXML AnchorPane vehicleInformationLabels;
	@FXML AnchorPane vehicleInformationInput;
	@FXML ClientResources resources;
	
	@FXML public void initalize() {
		resize();
	}
	
	public void resize() {
		AnchorPane.setTopAnchor(bottomPane, resources.getScreenHeight() * 0.6);
		AnchorPane.setBottomAnchor(topPane, resources.getScreenHeight() * 0.4);
		AnchorPane.setTopAnchor(clientInformationLabels, 0.0);
		AnchorPane.setRightAnchor(clientInformationLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(clientInformationInput, 0.0);
		AnchorPane.setLeftAnchor(clientInformationInput, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(vehicleInformationPane, 0.5);
		AnchorPane.setBottomAnchor(vehicleInformationPane, 0.1);
		AnchorPane.setTopAnchor(vehicleInformationLabels, resources.getScreenHeight() * 0.0125);
		AnchorPane.setRightAnchor(vehicleInformationLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(vehicleInformationInput, resources.getScreenHeight() * 0.0125);
		AnchorPane.setLeftAnchor(vehicleInformationInput, resources.getScreenWidth() * 0.5);
	}
}
