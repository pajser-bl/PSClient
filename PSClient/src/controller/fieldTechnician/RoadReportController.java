package controller.fieldTechnician;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.ClientCommunication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Intervention;
import utility.MessageBox;

public class RoadReportController {

	private ClientCommunication clientComm;
	private double stageHeight;
	private double stageWidth;
	private Stage reportStage;
	private Intervention intervention;
	@FXML Button reportButton;
	@FXML ComboBox<String> serviceBox;
	@FXML TextArea reportText;
	
	public RoadReportController(Stage reportStage, ClientCommunication clientComm, Intervention intervention, double stageWidth,
			double stageHeight) {
		this.reportStage = reportStage;
		this.clientComm = clientComm;
		this.intervention = intervention;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
	}
	
	public void sendReport(ActionEvent event) {
		ArrayList<String> reply = clientComm.newRoadReport(intervention.getId(), serviceBox.getValue(), LocalDateTime.now(),
				reportText.getText());
		if(reply.get(0).equals("NEW ROAD REPORT OK")) {
			intervention = null;
			MessageBox.displayMessage("Potvrda", "Terenski izvjestaj uspjesno poslan");
		} else 
			MessageBox.displayMessage("Greska", reply.get(1));
		reportStage.close();
	}
}
