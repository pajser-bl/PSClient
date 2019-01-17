package controller.fieldTechnician;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utility.ClientResources;

public class RoadReportController {

	@FXML AnchorPane interventionAnchor;
	@FXML AnchorPane vehicleAnchor;
	@FXML AnchorPane reportAnchor;
	@FXML Button submitButton;
	@FXML ComboBox<String> service;
	@FXML ClientResources resources;
	@FXML Label interventionId;
	@FXML Label date;
	@FXML Label time;
	@FXML Label licencePlate;
	@FXML Label manufacturer;
	@FXML Label yearMade;
	@FXML TextArea report;
	@FXML VBox interventionLabels;
	@FXML VBox interventionData;
	@FXML VBox vehicleLabels;
	@FXML VBox vehicleData;
	
	@FXML public void initialize() {
		resize();
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(interventionAnchor, resources.getScreenHeight() * 0.85);
		AnchorPane.setTopAnchor(interventionLabels, resources.getScreenHeight() * 0.05);
		AnchorPane.setRightAnchor(interventionLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(interventionData, resources.getScreenHeight() * 0.05);
		AnchorPane.setLeftAnchor(interventionData, resources.getScreenWidth() * 0.5);
		
		AnchorPane.setBottomAnchor(vehicleAnchor, resources.getScreenHeight() * 0.6);
		AnchorPane.setTopAnchor(vehicleAnchor, resources.getScreenHeight() * 0.15);
		AnchorPane.setTopAnchor(vehicleLabels, resources.getScreenHeight() * 0.0125);
		AnchorPane.setRightAnchor(vehicleLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setLeftAnchor(vehicleData, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(vehicleData, resources.getScreenHeight() * 0.0125);
		
		AnchorPane.setTopAnchor(reportAnchor, resources.getScreenHeight() * 0.4);
		AnchorPane.setBottomAnchor(report, resources.getScreenHeight() * 0.1);
		AnchorPane.setTopAnchor(report, resources.getScreenHeight() * 0.05);
	}
}
