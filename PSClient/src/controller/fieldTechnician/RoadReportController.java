package controller.fieldTechnician;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.ClientCommunication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.FieldTechnician;
import model.Intervention;
import model.Session;
import model.User;
import utility.MessageBox;

public class RoadReportController {

	private AnchorPane optionsPane;
	private ClientCommunication clientComm;
	private double stageHeight;
	private double stageWidth;
	private Stage reportStage;
	private Intervention intervention;
	private FieldTechnician user;
	private Label state;
	private Session session;
	@FXML Button reportButton;
	@FXML ComboBox<String> serviceBox;
	@FXML TextArea reportText;
	private FieldTechnicianController controller;
	
	@FXML public void initialize() {
		reportButton.setPrefSize(stageWidth * 0.3, stageHeight * 0.3);
	}

	public RoadReportController(Stage reportStage, FieldTechnicianController controller, ClientCommunication clientComm,
			FieldTechnician user, Session session, AnchorPane optionsPane, Intervention intervention, Label state,
			double stageWidth, double stageHeight) {
		this.reportStage = reportStage;
		this.clientComm = clientComm;
		this.optionsPane = optionsPane;
		this.session = session;
		this.intervention = intervention;
		this.controller = controller;
		this.stageWidth = stageWidth;
		this.state = state;
		this.user = user;
		this.stageHeight = stageHeight;
	}

	public void sendReport(ActionEvent event) {
		ArrayList<String> reply = clientComm.newRoadReport(intervention.getId(), serviceBox.getValue(),
				LocalDateTime.now(), reportText.getText());
		if (reply.get(0).equals("NEW ROAD REPORT OK")) {
			session.newFieldReport(intervention.getId());
			controller.setIntervention(null);
			user.setState("neaktivan");
			Platform.runLater( () -> {
				state.setText("Status: neaktivan");
			});
			optionsPane.getChildren().remove(1);
			MessageBox.displayMessage("Potvrda", "Terenski izvjestaj uspjesno poslan");
		} else
			MessageBox.displayMessage("Greska", reply.get(1));
		reportStage.close();
	}
}
