package controller.operater;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.ClientResources;

public class InterventionsController {
	
	@FXML AnchorPane anchor;
	@FXML TableView interventionsTable;
	@FXML ClientResources resources;
	
	
	@FXML public void initialize() {
		anchor.setMaxHeight(resources.getScreenHeight());
		anchor.setMaxWidth(resources.getScreenWidth());
	}
	
	public void openNewIntervention(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/operater/NewInterventionForm.fxml"));
			Stage interventionStage = new Stage();
			interventionStage.setScene(new Scene(root));
			interventionStage.initModality(Modality.APPLICATION_MODAL);
			interventionStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
