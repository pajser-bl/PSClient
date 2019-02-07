package controller.user;

import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Intervention;
import utility.TimeUtility;

public class InterventionController {

	private Intervention intervention;
	private double stageHeight;
	private double stageWidth;
	private String user;
	@FXML Button fieldReportButton;
	@FXML Button operaterReportButton;
	@FXML Button supervisorReportButton;
	@FXML HBox reportsBox;
	@FXML Label serviceLabel;
	@FXML Label serviceTimeLabel;
	@FXML Label closedLabel;
	@FXML Label operaterLabel;
	@FXML Label supervisorLabel;
	@FXML Label interventionId;
	@FXML Label date;
	@FXML Label time;
	@FXML Label operater;
	@FXML Label fieldTechnician;
	@FXML Label client;
	@FXML Label phoneNumber;
	@FXML Label vehicleModel;
	@FXML Label vehicleManu;
	@FXML Label vehicleYear;
	@FXML Label vehicleLicencePlate;
	@FXML Label service;
	@FXML Label serviceTime;
	@FXML Label closedTime;
	@FXML Label operaterClosed;
	@FXML Label supervisor;
	@FXML VBox infoLabelsBox;
	@FXML VBox infoBox;
	
	@FXML public void initialize() {
		setStage();
		setInfo();
		resize();
	}
	
	public InterventionController(Intervention intervention, String user, double stageWidth, double stageHeight) {
		this.intervention = intervention;
		this.stageHeight = stageHeight;
		this.stageWidth = stageWidth;
		this.user = user;
	}
	
	public void setStage() {
		if(user.equals("Operater") || user.equals("Field technician")) {
			reportsBox.getChildren().remove(supervisorReportButton);
			reportsBox.getChildren().remove(operaterReportButton);
			infoBox.getChildren().remove(supervisor);
			infoBox.getChildren().remove(operaterClosed);
			infoBox.getChildren().remove(closedTime);
			infoLabelsBox.getChildren().remove(supervisorLabel);
			infoLabelsBox.getChildren().remove(operaterLabel);
			infoLabelsBox.getChildren().remove(closedLabel);
		}
		if(user.equals("Field technician")) {
			reportsBox.getChildren().remove(fieldReportButton);
			infoBox.getChildren().remove(serviceTime);
			infoBox.getChildren().remove(service);
			infoLabelsBox.getChildren().remove(serviceTimeLabel);
			infoLabelsBox.getChildren().remove(serviceLabel);
		}
	}
	
	public void setInfo() {
		interventionId.setText(intervention.getId());
		date.setText(intervention.getOpenedOn().format(DateTimeFormatter.ISO_LOCAL_DATE));
		time.setText(intervention.getOpenedOn().format(DateTimeFormatter.ISO_LOCAL_TIME));
		operater.setText(intervention.getUserOpened());
		fieldTechnician.setText(intervention.getFieldTechnician());
		client.setText(intervention.getClient());
		phoneNumber.setText(intervention.getPhoneNumber());
		vehicleModel.setText(intervention.getVehicleModel());
		vehicleManu.setText(intervention.getVehicleManu());
		vehicleYear.setText(intervention.getVehicleYear());
		vehicleLicencePlate.setText(intervention.getVehicleLicencePlate());
		if(user.equals("Operater") && intervention.getState().equals("terenski izvjestaj")) {
			service.setText(intervention.getService());
			serviceTime.setText(TimeUtility.localDateTimeToString(intervention.getServiceTime()));
		}
	}
	
	public void showRoadReport(ActionEvent event) {
		AnchorPane anchor = new AnchorPane();
		TextField roadReport = new TextField();
		roadReport.appendText(intervention.getFieldReport());
		AnchorPane.setBottomAnchor(roadReport, 0.0);
		AnchorPane.setTopAnchor(roadReport, 0.0);
		AnchorPane.setLeftAnchor(roadReport, 0.0);
		AnchorPane.setRightAnchor(roadReport, 0.0);
		roadReport.setEditable(false);
		anchor.getChildren().add(roadReport);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(anchor, stageWidth, stageHeight * 0.5));
		stage.show();
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(infoBox, stageHeight * 0.1);
		AnchorPane.setLeftAnchor(infoBox, stageWidth * 0.5);
		AnchorPane.setBottomAnchor(infoLabelsBox, stageHeight * 0.1);
		AnchorPane.setRightAnchor(infoLabelsBox, stageWidth * 0.5);
		AnchorPane.setTopAnchor(reportsBox, stageHeight * 0.9);
	}
}
