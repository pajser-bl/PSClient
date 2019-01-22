package controller.operater;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utility.OperaterResources;

public class InterventionController {

	@FXML AnchorPane infoAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML Button closeInterventionButton;
	@FXML Button fieldReportButton;
	@FXML Label interventionId;
	@FXML Label date;
	@FXML Label time;
	@FXML Label operater;
	@FXML Label fieldTechnician;
	@FXML Label client;
	@FXML OperaterResources resources;
	@FXML VBox infoLabelsBox;
	@FXML VBox infoBox;
	
	@FXML public void initialize() {
		setInfo();
		if(resources.getIntervention().getFieldReport() == null)
			optionsAnchor.getChildren().clear();
		resize();
	}
	
	public void setInfo() {
		interventionId.setText(resources.getIntervention().getId());
		date.setText(resources.getIntervention().getOpenedOn().format(DateTimeFormatter.ISO_LOCAL_DATE));
		time.setText(resources.getIntervention().getOpenedOn().format(DateTimeFormatter.ISO_LOCAL_TIME));
		operater.setText(resources.getIntervention().getUserOpened());
		fieldTechnician.setText(resources.getIntervention().getFieldTechnician());
		client.setText(resources.getIntervention().getClient());
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(infoAnchor, resources.getScreenHeight() * 0.1);
		AnchorPane.setRightAnchor(infoLabelsBox, resources.getScreenWidth() * 0.5);
		AnchorPane.setLeftAnchor(infoBox, resources.getScreenWidth() * 0.5);
		
		AnchorPane.setTopAnchor(optionsAnchor, resources.getScreenHeight() * 0.9);
		closeInterventionButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
		fieldReportButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
	}
}
