package controller.operater;

import java.time.format.DateTimeFormatter;

import client.Intervention;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class InterventionController {

	private Intervention intervention;
	private double stageHeight;
	private double stageWidth;
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
	@FXML Label vehicleModel;
	@FXML Label vehicleManu;
	@FXML Label vehicleYear;
	@FXML Label vehicleLicencePlate;
	@FXML VBox infoLabelsBox;
	@FXML VBox infoBox;
	
	@FXML public void initialize() {
		setInfo();
		resize();
	}
	
	public InterventionController(Intervention intervention, double stageWidth, double stageHeight) {
		this.intervention = intervention;
		this.stageHeight = stageHeight;
		this.stageWidth = stageWidth;
	}
	
	public void setInfo() {
		interventionId.setText(interventionId.getId());
		date.setText(intervention.getOpenedOn().format(DateTimeFormatter.ISO_LOCAL_DATE));
		time.setText(intervention.getOpenedOn().format(DateTimeFormatter.ISO_LOCAL_TIME));
		operater.setText(intervention.getUserOpened());
		fieldTechnician.setText(intervention.getFieldTechnician());
		client.setText(intervention.getClient());
	}
	
	public void resize() {}
}
