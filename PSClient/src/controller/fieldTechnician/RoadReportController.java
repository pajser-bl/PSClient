package controller.fieldTechnician;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RoadReportController {

	private double stageHeight;
	private double stageWidth;
	private Stage reportStage;
	@FXML AnchorPane interventionAnchor;
	@FXML AnchorPane vehicleAnchor;
	@FXML AnchorPane reportAnchor;
	@FXML Button submitButton;
	@FXML ComboBox<String> service;
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
		AnchorPane.setBottomAnchor(interventionAnchor, stageHeight * 0.85);
		AnchorPane.setTopAnchor(interventionLabels, stageHeight * 0.05);
		AnchorPane.setRightAnchor(interventionLabels, stageWidth * 0.5);
		AnchorPane.setTopAnchor(interventionData, stageHeight * 0.05);
		AnchorPane.setLeftAnchor(interventionData, stageWidth * 0.5);
		
		AnchorPane.setBottomAnchor(vehicleAnchor, stageHeight * 0.6);
		AnchorPane.setTopAnchor(vehicleAnchor, stageHeight * 0.15);
		AnchorPane.setTopAnchor(vehicleLabels, stageHeight * 0.0125);
		AnchorPane.setRightAnchor(vehicleLabels, stageWidth * 0.5);
		AnchorPane.setLeftAnchor(vehicleData, stageWidth * 0.5);
		AnchorPane.setTopAnchor(vehicleData, stageHeight * 0.0125);
		
		AnchorPane.setTopAnchor(reportAnchor, stageHeight * 0.4);
		AnchorPane.setBottomAnchor(report, stageHeight * 0.1);
		AnchorPane.setTopAnchor(report, stageHeight * 0.05);
	}
}
