package controller.fieldTechnician;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.ClientCommunication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Intervention;
import utility.MessageBox;

public class RoadReportController {

	private AnchorPane optionsPane;
	private ClientCommunication clientComm;
	private double stageHeight;
	private double stageWidth;
	private Stage reportStage;
	private Intervention intervention;
	@FXML
	Button reportButton;
	@FXML
	ComboBox<String> serviceBox;
	@FXML
	TextArea reportText;
	
	@FXML public void initialize() {
		reportButton.setPrefSize(stageWidth * 0.3, stageHeight * 0.3);
	}

	public RoadReportController(Stage reportStage, ClientCommunication clientComm, AnchorPane optionsPane, Intervention intervention,
			double stageWidth, double stageHeight) {
		this.reportStage = reportStage;
		this.clientComm = clientComm;
		this.optionsPane = optionsPane;
		this.intervention = intervention;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
	}

	public void sendReport(ActionEvent event) {
		ArrayList<String> reply = clientComm.newRoadReport(intervention.getId(), serviceBox.getValue(),
				LocalDateTime.now(), reportText.getText());
		if (reply.get(0).equals("NEW ROAD REPORT OK")) {
			intervention = null;
			optionsPane.getChildren().remove(3);
			MessageBox.displayMessage("Potvrda", "Terenski izvjestaj uspjesno poslan");
		} else
			MessageBox.displayMessage("Greska", reply.get(1));
		reportStage.close();
	}
}
