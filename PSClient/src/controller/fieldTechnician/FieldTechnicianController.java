	package controller.fieldTechnician;

import java.util.ArrayList;

import client.FieldTechnician;
import client.Session;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.ChoiceBox;
import utility.ClientResources;
import utility.MessageBox;

public class FieldTechnicianController {

	private Session session;
	private FieldTechnician user;
	@FXML AnchorPane avatarAnchor;
	@FXML AnchorPane statusAnchor;
	@FXML AnchorPane menuAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML AnchorPane workspaceAnchor;
	@FXML Button stateButton;
	@FXML Button mapButton;
	@FXML Button reportButton;
	@FXML Button sessionButton;
	@FXML Button logoutButton;
	@FXML Button refreshButton;
	@FXML Button helpButton;
	@FXML ClientResources resources;
	@FXML ImageView avatar;
	@FXML VBox userData;
	
	@FXML public void initialize() {
		user = new FieldTechnician(resources.getUser());
		resize();
		resources.getStage().setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public void showSession(ActionEvent event) {
		if(!workspaceAnchor.getChildren().isEmpty())
			workspaceAnchor.getChildren().clear();
		TextArea sessionTextArea = new TextArea();
		sessionTextArea.setText(session.toString());
		workspaceAnchor.getChildren().add(sessionTextArea);
	}
	
	public void newFieldReport(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/fieldTechnician/ReportForm.fxml"));
			if(workspaceAnchor.getChildren().size() != 0)
				workspaceAnchor.getChildren().remove(0);
			workspaceAnchor.getChildren().add(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeState(ActionEvent event) {
		if(user.getState().equals("aktivan")) {
			user.setState("neaktivan");
		} else user.setState("aktivan");
		ArrayList<String> reply = resources.getClientCommunication().changeState(resources.getUser().getUserId(),user.getState());
		if(reply.get(0).equals("CHANGE STATE OK"))
				MessageBox.displayMessage("Potvrda", "Stanje uspjesno pormjenjeno");
		else {
			if(user.getState().equals("aktivan")) {
				user.setState("neaktivan");
			} else user.setState("aktivan");
			
				
			MessageBox.displayMessage("Greska", "Greska pri promjeni stanja");
		}
	}
	
	public void showMap(ActionEvent event) {
		ClientResources reportResources = new ClientResources(null, resources.getClientCommunication(), resources.getUser(),
				resources.getScreenWidth() * 0.33, resources.getScreenHeight() * 0.7);
		try {
			Stage addNewUserStage = new Stage();
			addNewUserStage.setResizable(false);
			addNewUserStage.initModality(Modality.APPLICATION_MODAL);
			Parent root = FXMLLoader.load(getClass().getResource("/new_forms/NewRoadReportForm.fxml"), reportResources);
			Scene addNewUserScene = new Scene(root, reportResources.getScreenWidth(), reportResources.getScreenHeight());
			addNewUserStage.setScene(addNewUserScene);
			addNewUserStage.show();
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
	
	public void logout() {
		close();
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
		stateButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		mapButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		reportButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		sessionButton.setPrefSize(resources.getScreenWidth() * 0.2, resources.getScreenHeight() * 0.1125);
		logoutButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		refreshButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
		helpButton.setPrefSize(resources.getScreenWidth() * 0.1, resources.getScreenHeight() * 0.15);
	}
}
