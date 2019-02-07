package controller.operater;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.ClientCommunication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Event;
import model.Intervention;
import model.Session;
import model.User;
import utility.MessageBox;
import utility.TimeUtility;

public class CloseInterventionController {

	private ClientCommunication clientComm;
	private ObservableList<Intervention> interventions;
	private User user;
	private Stage reportStage;
	private Intervention intervention;
	private Session session;
	@FXML Button reportButton;
	@FXML TextArea reportText;
	
	public CloseInterventionController(Stage reportStage, ClientCommunication clientComm, User user, Session session,
			ObservableList<Intervention> interventions, Intervention intervention) {
		this.reportStage = reportStage;
		this.clientComm = clientComm;
		this.session = session;
		this.user = user;
		this.interventions = interventions;
		this.intervention = intervention;
	}
	
	public void sendReport(ActionEvent event) {
		ArrayList<String> reply = clientComm.closeIntervention(intervention.getId(), user.getUserId(),
				TimeUtility.localDateTimeToString(LocalDateTime.now()), reportText.getText());
		try {
			if(reply.get(0).equals("CLOSE INTERVENTION OK")) {
				session.getEventList().add(new Event("Korisnik " + user.getName() + " " + user.getLastName() +
						" je zatvorio intervenciju " + intervention.getId()));
				interventions.remove(intervention);
				MessageBox.displayMessage("Potvrda", "Intervencija uspjesno zatvorena");
				reportStage.close();
			} else
				MessageBox.displayMessage("Greska", reply.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
