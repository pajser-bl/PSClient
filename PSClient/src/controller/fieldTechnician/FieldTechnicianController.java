package controller.fieldTechnician;

import client.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import utility.ChoiceBox;
import utility.ClientResources;
import utility.TestingClass;

public class FieldTechnicianController {

	private Session session;
	@FXML AnchorPane anchor;
	@FXML Button stateButton;
	@FXML Button mapButton;
	@FXML Button fieldReportButton;
	@FXML Button sessionButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button helpButton;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		/*resources.getStage().setOnCloseRequest(e -> {
			e.consume();
			close();
		});*/
	}
	
	public void showSession(ActionEvent event) {
		session = TestingClass.generateSession();
		if(!anchor.getChildren().isEmpty())
			anchor.getChildren().clear();
		TextArea sessionTextArea = new TextArea();
		sessionTextArea.setText(session.toString());
		anchor.getChildren().add(sessionTextArea);
	}
	
	public void newFieldReport(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/fieldTechnician/ReportForm.fxml"));
			if(anchor.getChildren().size() != 0)
				anchor.getChildren().remove(0);
			anchor.getChildren().add(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		boolean answer = ChoiceBox.displayChoice("Odjava", "Da li ste sigurni da zelite da se odjavite?");
		if(answer) {
			resources.getClientCommunication().logout(resources.getUser().getUserId());
			resources.getClientCommunication().closeConnection();
			resources.getStage().hide();
		}
	}
}
