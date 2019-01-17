package controller.operater;

import utility.ChoiceBox;
import utility.ClientResources;
import utility.OperaterResources;
import utility.MessageBox;
import utility.AdministratorResources;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

import client.FieldTechnitian;
import client.Request;
import client.Session;
import client.User;
import exception.ServerReplyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class OperaterController {

	private Session session;
	@FXML AnchorPane statusAnchor;
	@FXML AnchorPane menuAnchor;
	@FXML AnchorPane workspaceAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML Button interventionsButton;
	@FXML Button mapButton;
	@FXML Button sessionButton;
	@FXML Button vehiclesButton;
	@FXML Button helpButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML BorderPane mainLayout;
	@FXML ClientResources resources;
	@FXML Label name;
	@FXML Label lastName;
	
	@FXML public void initialize() {
		session = new Session();
		resize();
		//name.setText("Ime: " + resources.getUser().getName());
		//lastName.setText("Prezime: " + resources.getUser().getLastName());
		resources.getStage().setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public void showInterventions(ActionEvent event) {
		
		if(!workspaceAnchor.getChildren().isEmpty())
			workspaceAnchor.getChildren().clear();
		try {
			OperaterResources operaterResources = new OperaterResources(resources, null, session);
			Parent root = FXMLLoader.load(getClass().getResource("/view/operater/InterventionsForm.fxml"), operaterResources);
			workspaceAnchor.getChildren().add(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showSession(ActionEvent event) {
		if(!workspaceAnchor.getChildren().isEmpty())
			workspaceAnchor.getChildren().clear();
		TextArea sessionTextArea = new TextArea();
		sessionTextArea.setText(session.toString());
		workspaceAnchor.getChildren().add(sessionTextArea);
	}
	
	public void showFieldTechnicians(ActionEvent event) {
		Request request = new Request("VIEW FIELD TECHNITIANS", new ArrayList<String>());
		ArrayList<String> reply = resources.getClientCommunication().sendRequest(request);
		if(reply.get(0).equals("VIEW FIELD TECHNICIANS OK") && (Integer.parseInt(reply.get(1)) != 0)) {
			ArrayList<FieldTechnitian> fieldTechnitians = new ArrayList<>();
			for (int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsedUser = reply.get(i + 2).split(":");
				fieldTechnitians.add(new FieldTechnitian(parsedUser[0], parsedUser[1], parsedUser[2], parsedUser[3]));
			}
			if(!workspaceAnchor.getChildren().isEmpty())
					workspaceAnchor.getChildren().clear();
			try {
				OperaterResources tableResources = new OperaterResources(resources, fieldTechnitians, session);
				Parent root = FXMLLoader.load(getClass().getResource("/view/operater/FieldTechnicianTableForm.fxml"), tableResources);
				workspaceAnchor.getChildren().add(root);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (Integer.parseInt(reply.get(1)) == 0) {
			MessageBox.displayMessage("Greska", "Nema prijavljenih terenskih radnika");
		} else MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void showMap(ActionEvent event) {}
	
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
	
	public void resize() {
		AnchorPane.setBottomAnchor(statusAnchor, resources.getScreenHeight() * 0.8);
		AnchorPane.setTopAnchor(menuAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setRightAnchor(menuAnchor, resources.getScreenWidth() * 0.8);
		AnchorPane.setTopAnchor(workspaceAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(workspaceAnchor, resources.getScreenWidth() * 0.2);
		AnchorPane.setRightAnchor(workspaceAnchor, resources.getScreenWidth() * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(optionsAnchor, resources.getScreenWidth() * 0.9);
		interventionsButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		mapButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		sessionButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		vehiclesButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		logoutButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		refreshButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		helpButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
	}
}
