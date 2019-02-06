package controller.operater;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.ClientCommunication;
import exception.MessageException;
import exception.ServerReplyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Event;
import model.FieldTechnician;
import model.Request;
import model.Session;
import model.User;
import utility.TimeUtility;
import utility.MessageBox;

public class NewInterventionController {

	private ClientCommunication clientComm;
	private ArrayList<FieldTechnician> technicians;
	private User user;
	private double stageHeight;
	private double stageWidth;
	private Stage interventionStage;
	private Session session;
	@FXML AnchorPane clientInformationAnchor;
	@FXML AnchorPane clientInformationLabels;
	@FXML AnchorPane clientInformationInput;
	@FXML AnchorPane vehicleAnchor;
	@FXML AnchorPane vehicleInformationAnchor;
	@FXML AnchorPane vehicleInformationLabels;
	@FXML AnchorPane vehicleInformationInput;
	@FXML ComboBox<String> fieldTechniciansBox;
	@FXML Button submitButton;
	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField phoneNumber;
	@FXML TextField licencePlate;
	@FXML TextField manufacturer;
	@FXML TextField model;
	@FXML TextField yearMade;
	
	@FXML public void initialize() {
		for(int i = 0; i < technicians.size(); i++) {
			fieldTechniciansBox.getItems().add(technicians.get(i).toStringNoState());
		}
		fieldTechniciansBox.setValue(technicians.get(0).toStringNoState());
		resize();
	}
	
	public NewInterventionController(Stage interventionStage, ClientCommunication clientComm, User user,
			ArrayList<FieldTechnician> technicians, Session session, double stageWidth, double stageHeight) {
		this.interventionStage = interventionStage;
		this.clientComm = clientComm;
		this.user = user;
		this.technicians = technicians;
		this.session = session;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
	}
	
	public void openNewIntervention(ActionEvent event) {
		try {
			if(name.getText().isEmpty())
				throw new MessageException("Ime klijenta mora biti popunjeno");
			if(lastName.getText().isEmpty())
				throw new MessageException("Prezime klijenta mora biti popunjeno");
			if(phoneNumber.getText().isEmpty())
				throw new MessageException("Broj telefona klijenta mora biti popunjeno");
			ArrayList<String> arguments = new ArrayList<String>();
			arguments.add(user.getUserId());
			arguments.add(TimeUtility.localDateTimeToString(LocalDateTime.now()));
			arguments.add(name.getText());
			arguments.add(lastName.getText());
			arguments.add(phoneNumber.getText());
			arguments.add(licencePlate.getText());
			if(!model.getText().isEmpty())
				arguments.add(model.getText());
			else arguments.add("empty");
			if(!manufacturer.getText().isEmpty())
				arguments.add(manufacturer.getText());
			else arguments.add("empty");
			if(!yearMade.getText().isEmpty())
				arguments.add(yearMade.getText());
			else arguments.add("2000");
			String[] fieldTechnician = fieldTechniciansBox.getSelectionModel().getSelectedItem().split(":");
			arguments.add(fieldTechnician[0]);
			Request request = new Request("NEW INTERVENTION", arguments);
			ArrayList<String> reply = clientComm.sendRequest(request);
			if(reply.get(0).equals("NEW INTERVENTION NOT OK"))
				throw new ServerReplyException(reply.get(1));
			session.getEventList().add(new Event("Korisnik " + user.getName() + " " + user.getLastName() + " je otvorio novu intervenciju"));
			MessageBox.displayMessage("Potvrda", "Intervencija je otvorena");
			interventionStage.close();
		} catch (MessageException e) {
			MessageBox.displayMessage("Greska", e.toString());
		} catch (ServerReplyException e) {
			MessageBox.displayMessage("Greska", e.toString());
		}
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(clientInformationAnchor, stageHeight * 0.6);
		AnchorPane.setTopAnchor(clientInformationLabels, 0.0);
		AnchorPane.setRightAnchor(clientInformationLabels, stageWidth * 0.5);
		AnchorPane.setTopAnchor(clientInformationInput, 0.0);
		AnchorPane.setLeftAnchor(clientInformationInput, stageWidth * 0.5);
		AnchorPane.setTopAnchor(vehicleAnchor, stageHeight * 0.4);
		AnchorPane.setTopAnchor(vehicleInformationAnchor, 0.5);
		AnchorPane.setBottomAnchor(vehicleInformationAnchor, 0.1);
		AnchorPane.setTopAnchor(vehicleInformationLabels, stageHeight * 0.0125);
		AnchorPane.setRightAnchor(vehicleInformationLabels, stageWidth * 0.5);
		AnchorPane.setTopAnchor(vehicleInformationInput, stageWidth * 0.0125);
		AnchorPane.setLeftAnchor(vehicleInformationInput, stageWidth * 0.5);
	}
}
