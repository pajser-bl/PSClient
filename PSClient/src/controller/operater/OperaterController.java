package controller.operater;

import utility.ChoiceBox;
import utility.ClientResources;
import utility.OperaterResources;
import utility.TimeUtility;
import utility.MessageBox;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

import client.Event;
import client.FieldTechnician;
import client.Intervention;
import client.Request;
import client.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class OperaterController {

	private Session session;
	@FXML AnchorPane statusAnchor;
	@FXML AnchorPane menuAnchor;
	@FXML AnchorPane workspaceAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML AnchorPane avatarAnchor;
	@FXML Button interventionsButton;
	@FXML Button mapButton;
	@FXML Button sessionButton;
	@FXML Button vehiclesButton;
	@FXML Button helpButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML BorderPane mainLayout;
	@FXML ClientResources resources;
	@FXML ImageView avatar;
	@FXML Label name;
	@FXML Label lastName;
	@FXML VBox userData;
	
	@FXML public void initialize() {
		session = new Session();
		session.getEventList().add(new Event("Korisnik " + resources.getUser().getName() + " " + resources.getUser().getLastName() +
				" se prijavio na sistem"));
		resize();
		name.setText("  " + resources.getUser().getName());
		lastName.setText("  " + resources.getUser().getLastName());
		resources.getStage().setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public void showInterventions(ActionEvent event) {
		ArrayList<String> reply = resources.getClientCommunication().showOpenedInterventions();
		ArrayList<Intervention> interventions = new ArrayList<>();
		OperaterResources operaterResources = new OperaterResources(resources, null, session, interventions);
		System.out.print(reply.toString());
		if(reply.get(0).equals("VIEW OPENED INTERVENTIONS OK")) {
			for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsedReply = reply.get(i + 2).split(":");
				parsedReply[3].replace(";", ":");
				Intervention intervention = new Intervention(parsedReply[0], parsedReply[1], parsedReply[2],
						TimeUtility.stringToLocalDateTime(parsedReply[3]), parsedReply[4]);
				interventions.add(intervention);
			}
		}
		if(!workspaceAnchor.getChildren().isEmpty())
			workspaceAnchor.getChildren().clear();
		try {
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
		AnchorPane.setBottomAnchor(sessionTextArea, 0.0);
		AnchorPane.setTopAnchor(sessionTextArea, 0.0);
		AnchorPane.setLeftAnchor(sessionTextArea, 0.0);
		AnchorPane.setRightAnchor(sessionTextArea, 0.0);
		sessionTextArea.getStylesheets().add("@../../css/background.css");
		sessionTextArea.getStyleClass().add("root9");
		sessionTextArea.setStyle("root9");
		workspaceAnchor.getChildren().add(sessionTextArea);
	}
	
	public void showFieldTechnicians(ActionEvent event) {
		Request request = new Request("VIEW FIELD TECHNICIANS", new ArrayList<String>());
		ArrayList<String> reply = resources.getClientCommunication().sendRequest(request);
		if(reply.get(0).equals("VIEW FIELD TECHNICIANS OK") && (Integer.parseInt(reply.get(1)) != 0)) {
			ArrayList<FieldTechnician> fieldTechnicians = new ArrayList<>();
			for (int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsedUser = reply.get(i + 2).split(":");
				fieldTechnicians.add(new FieldTechnician(parsedUser[0], parsedUser[1], parsedUser[2], parsedUser[3]));
			}
			if(!workspaceAnchor.getChildren().isEmpty())
					workspaceAnchor.getChildren().clear();
			try {
				OperaterResources tableResources = new OperaterResources(resources, fieldTechnicians, session, null);
				Parent root = FXMLLoader.load(getClass().getResource("/view/operater/FieldTechnicianTableForm.fxml"), tableResources);
				workspaceAnchor.getChildren().add(root);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (reply.get(0).equals("VIEW FIELD TECHNICIANS OK") && (Integer.parseInt(reply.get(1)) == 0)) {
			MessageBox.displayMessage("Greska", "Nema prijavljenih terenskih radnika");
			workspaceAnchor.getChildren().clear();
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
		AnchorPane.setBottomAnchor(statusAnchor, resources.getScreenHeight() * 0.7715);
		AnchorPane.setTopAnchor(menuAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setRightAnchor(menuAnchor, resources.getScreenWidth() * 0.8);
		AnchorPane.setTopAnchor(workspaceAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(workspaceAnchor, resources.getScreenWidth() * 0.2);
		AnchorPane.setRightAnchor(workspaceAnchor, resources.getScreenWidth() * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, resources.getScreenHeight() * 0.2);
		AnchorPane.setLeftAnchor(optionsAnchor, resources.getScreenWidth() * 0.9);
		AnchorPane.setRightAnchor(avatarAnchor, resources.getScreenWidth() * 0.9);
		AnchorPane.setLeftAnchor(userData, resources.getScreenWidth() * 0.1);
		AnchorPane.setRightAnchor(userData, resources.getScreenWidth() * 0.8);
		avatar.setFitHeight(resources.getScreenHeight() * 0.8);
		avatar.setFitWidth(resources.getScreenWidth() * 0.1);
		interventionsButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		mapButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		sessionButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		vehiclesButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		logoutButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		refreshButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		helpButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
	}
}
