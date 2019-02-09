package controller.supervisor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.ClientCommunication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import utility.MessageBox;
import utility.TimeUtility;

public class ReportController {

	private ClientCommunication clientComm;
	private String userId;
	private String interventionId;
	@FXML TextArea reportText;
	@FXML Button reportButton;
	
	public ReportController(ClientCommunication clientComm, String interventionId, String userId) {
		this.clientComm = clientComm;
		this.interventionId = interventionId;
		this.userId = userId;
	};
	
	public void sendReport() {
		ArrayList<String> reply = clientComm.
				newReport(interventionId, userId, reportText.getText(), TimeUtility.localDateTimeToString(LocalDateTime.now()));
		if(reply.get(0).equals("NEW REPORT OK"))
			MessageBox.displayMessage("Potvrda", "Izvjestaj uspjesno poslan");
		else
			MessageBox.displayMessage("Greska", reply.get(1));
	}
}
