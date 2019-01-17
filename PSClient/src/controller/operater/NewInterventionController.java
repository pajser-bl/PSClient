package controller.operater;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.Request;
import exception.EmptyFieldException;
import exception.ServerReplyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utility.OperaterResources;
import utility.MessageBox;

public class NewInterventionController {

	@FXML AnchorPane clientInformationAnchor;
	@FXML AnchorPane clientInformationLabels;
	@FXML AnchorPane clientInformationInput;
	@FXML AnchorPane vehicleAnchor;
	@FXML AnchorPane vehicleInformationAnchor;
	@FXML AnchorPane vehicleInformationLabels;
	@FXML AnchorPane vehicleInformationInput;
	@FXML ComboBox<String> fieldTechnicians;
	@FXML OperaterResources resources;
	@FXML Button submitButton;
	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField phoneNumber;
	@FXML TextField licencePlate;
	@FXML TextField manufacturer;
	@FXML TextField model;
	@FXML TextField yearMade;
	
	@FXML public void initialize() {
		ArrayList<String> fieldTechnitians = new ArrayList<>();
		for(int i = 0; i < resources.getFieldTechnitians().size(); i++) {
			fieldTechnitians.add(resources.getFieldTechnitians().get(i).toStringNoState());
		}
		fieldTechnicians.getItems().addAll(fieldTechnitians);
		fieldTechnicians.setValue(fieldTechnitians.get(0));
		resize();
	}
	
	public void openNewIntervention(ActionEvent event) {
		try {
			if(name.getText().isEmpty() || lastName.getText().isEmpty())
				throw new EmptyFieldException();
			ArrayList<String> arguments = new ArrayList<String>();
			LocalDateTime openedOn = LocalDateTime.now();
			arguments.add(resources.getUser().getUserId());
			arguments.add(openedOn.toString());
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
			else arguments.add("empty");
			String[] fieldTechnician = fieldTechnicians.getSelectionModel().getSelectedItem().split(":");
			arguments.add(fieldTechnician[0]);
			Request request = new Request("NEW INTERVENTION", arguments);
			ArrayList<String> reply = resources.getClientCommunication().sendRequest(request);
			if(reply.get(0).equals("NEW INTERVENTION NOT OK"))
				throw new ServerReplyException(reply.get(1));
			MessageBox.displayMessage("Potvrda", "Intervencija je otvorena");
			resources.getStage().close();
		} catch (EmptyFieldException e) {
			MessageBox.displayMessage("Greska", "Oznacena polja moraju biti popunjena");
		} catch (ServerReplyException e) {
			MessageBox.displayMessage("Greska", e.toString());
		}
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(clientInformationAnchor, resources.getScreenHeight() * 0.6);
		AnchorPane.setTopAnchor(clientInformationLabels, 0.0);
		AnchorPane.setRightAnchor(clientInformationLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(clientInformationInput, 0.0);
		AnchorPane.setLeftAnchor(clientInformationInput, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(vehicleAnchor, resources.getScreenHeight() * 0.4);
		AnchorPane.setTopAnchor(vehicleInformationAnchor, 0.5);
		AnchorPane.setBottomAnchor(vehicleInformationAnchor, 0.1);
		AnchorPane.setTopAnchor(vehicleInformationLabels, resources.getScreenHeight() * 0.0125);
		AnchorPane.setRightAnchor(vehicleInformationLabels, resources.getScreenWidth() * 0.5);
		AnchorPane.setTopAnchor(vehicleInformationInput, resources.getScreenHeight() * 0.0125);
		AnchorPane.setLeftAnchor(vehicleInformationInput, resources.getScreenWidth() * 0.5);
	}
}
