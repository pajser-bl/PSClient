package new_forms;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import utility.ClientResources;

public class NewAddNewUserController {

	@FXML AnchorPane bottomPane;
	@FXML AnchorPane accountSettingsPane;
	@FXML AnchorPane accountSettingsLabels;
	@FXML AnchorPane accountSettingsInput;
	@FXML AnchorPane userDataInput;
	@FXML AnchorPane userDataLabels;
	@FXML AnchorPane topPane;
	@FXML DatePicker date;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		resize();
	}
	
	public void resize() {
		AnchorPane.setTopAnchor(bottomPane, resources.getScreenHeight() * 0.6);
		AnchorPane.setBottomAnchor(topPane, resources.getScreenHeight() * 0.4);
		AnchorPane.setTopAnchor(userDataLabels, 0.0);
		AnchorPane.setRightAnchor(userDataLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(userDataInput, 0.0);
		AnchorPane.setLeftAnchor(userDataInput, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(accountSettingsPane, 0.5);
		AnchorPane.setBottomAnchor(accountSettingsPane, 0.1);
		AnchorPane.setTopAnchor(accountSettingsLabels, resources.getScreenHeight() * 0.0125);
		AnchorPane.setRightAnchor(accountSettingsLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(accountSettingsInput, resources.getScreenHeight() * 0.0125);
		AnchorPane.setLeftAnchor(accountSettingsInput, resources.getScreenWidth() * 0.5);
	}
}
