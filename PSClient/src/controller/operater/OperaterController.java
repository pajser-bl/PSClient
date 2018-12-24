package controller.operater;

import utility.ChoiceBox;
import utility.ClientResources;
import utility.TestingClass;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

import client.Request;
import client.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class OperaterController {

	private Session session;
	@FXML Button interventionsButton;
	@FXML Button mapButton;
	@FXML Button sessionButton;
	@FXML Button FieldTechniciansButton;
	@FXML Button helpButton;
	@FXML Button logoutButton;
	@FXML BorderPane mainLayout;
	@FXML Label name;
	@FXML Label lastName;
	@FXML AnchorPane anchor;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		/*session = new Session();
		name.setText("Ime: " + resources.getUser().getName());
		lastName.setText("Prezime: " + resources.getUser().getLastName());
		resources.getStage().setOnCloseRequest(e -> {
			e.consume();
			close();
		});*/
	}
	
	public void showSession(ActionEvent event) {
		if(!anchor.getChildren().isEmpty())
			anchor.getChildren().clear();
		TextArea sessionTextArea = new TextArea();
		sessionTextArea.setText(session.toString());
		anchor.getChildren().add(sessionTextArea);
	}
	
	public void showInterventions(ActionEvent event) {
		if(!anchor.getChildren().isEmpty())
			anchor.getChildren().clear();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/operater/InterventionsForm.fxml"), resources);
			anchor.getChildren().add(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showFieldTechnicians(ActionEvent event) {
		Request request = new Request("VIEW FIELD TECHNITIANS", new ArrayList<String>());
		ArrayList<String> reply = resources.getClientCommunication().sendRequest(request);
		for(int i = 0; i < reply.size(); i++)
			System.out.println(reply.get(i));
		if(!anchor.getChildren().isEmpty())
			anchor.getChildren().clear();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/operater/VehicleForm.fxml"), resources);
			anchor.getChildren().add(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showMap(ActionEvent event) {
		
	}
	
	public void logout(ActionEvent logout) {
		close();
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
