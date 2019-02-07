package controller.fieldTechnician;

import client.ClientCommunication;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Event;
import model.FieldTechnician;
import model.Intervention;
import model.Session;
import utility.ChoiceBox;
import utility.MessageBox;
import utility.TimeUtility;

public class FieldTechnicianController {

	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	private FieldTechnician user;
	private Intervention intervention = null;
	private Session session;
	private Stage mainStage;
	@FXML AnchorPane avatarAnchor;
	@FXML AnchorPane statusAnchor;
	@FXML AnchorPane stateAnchor;
	@FXML AnchorPane menuAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML AnchorPane workspaceAnchor;
	@FXML Button stateButton;
	@FXML Button mapButton;
	@FXML Button reportButton;
	@FXML Button sessionButton;
	@FXML Button logoutButton;
	@FXML Button helpButton;
	@FXML ImageView avatar;
	@FXML ImageView stateImage;
	@FXML Label name;
	@FXML Label lastName;
	@FXML Label stateLabel;
	@FXML VBox userData;

	@FXML
	public void initialize() {
		resize();
		name.setText("  " + user.getName());
		lastName.setText("  " + user.getLastName());
		stateLabel.setText("Stanje: " + user.getState());
		
		mainStage.setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}

	public FieldTechnicianController(Stage mainStage, ClientCommunication clientComm, FieldTechnician user,
			double screenWidth, double screenHeight) {
		this.mainStage = mainStage;
		this.clientComm = clientComm;
		this.user = user;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.session = new Session();
		session.getEventList()
				.add(new Event("Korisnik " + user.getName() + " " + user.getLastName() + " se prijavio na sistem"));
	}

	public void showSession(ActionEvent event) {
		if (!workspaceAnchor.getChildren().isEmpty())
			workspaceAnchor.getChildren().clear();
		TextArea sessionTextArea = new TextArea();
		sessionTextArea.setText(session.toString());
		workspaceAnchor.getChildren().add(sessionTextArea);
	}

	public void newFieldReport(ActionEvent event) {
		if (intervention != null) {
			Stage reportStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/field_technician/RoadReportForm.fxml"));
			loader.setControllerFactory(e -> new RoadReportController(reportStage, clientComm, optionsAnchor, intervention,
					screenWidth * 0.3, screenHeight * 0.3));
			try {
				Parent root = loader.load();
				reportStage.setResizable(false);
				reportStage.initModality(Modality.APPLICATION_MODAL);
				reportStage.setScene(new Scene(root, screenWidth * 0.3, screenHeight * 0.3));
				reportStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			MessageBox.displayMessage("Greska", "Nemate aktivnu intervenciju");
	}

	public void changeState(ActionEvent event) {
		String oldState = user.getState();
		if(user.getState().equals("neaktivan"))
			user.setState("aktivan");
		else
			user.setState("neaktivan");
		ArrayList<String> reply = clientComm.changeState(user.getId(), user.getState());
		if (reply.get(0).equals("CHANGE STATE OK")) {
			Platform.runLater( () -> {
				session.getEventList().add(new Event("Promjena u " + user.getState()));
				stateLabel.setText("Stanje: " + user.getState());
				if(user.getState().equals("aktivan"))
					stateImage.setImage(new Image("/resources/images/circle_blue.png"));
				else stateImage.setImage(new Image("/resources/images/circle_red.png"));
			});
			MessageBox.displayMessage("Potvrda", "Stanje uspjesno promjenjeno");
		} else {
			user.setState(oldState);
			MessageBox.displayMessage("Greska", "Greska pri promjeni stanja");
		}
	}

	public void interventionAlert(Intervention intervention) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/view/field_technician/InterventionAlertForm.fxml"));
			loader.setControllerFactory(
					e -> new InterventionAlertController(intervention, screenWidth * 0.3, screenHeight * 0.2));
			Parent interventionAlert = loader.load();
			optionsAnchor.getChildren().add(interventionAlert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkOpenedIntervention(ActionEvent event) {
		ArrayList<String> reply = clientComm.checkOpenedIntervention(user.getId());
		if (reply.get(0).equals("CHECK FIELD TECHNICIAN INTERVENTION OK")) {
			user.setState("zauzet");
			intervention = new Intervention(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5),
					reply.get(6), reply.get(7), reply.get(8), reply.get(9),
					TimeUtility.stringToLocalDateTime(reply.get(10)), reply.get(11), "", LocalDateTime.now(), "", "",
					LocalDateTime.now(), "", "", "", LocalDateTime.now());
			interventionAlert(intervention);
		} else {
			MessageBox.displayMessage("Greska", reply.get(1));
		}
	}

	public void close() {
		boolean answer = ChoiceBox.displayChoice("Odjava", "Da li ste sigurni da zelite da se odjavite?");
		if (answer) {
			clientComm.logout(user.getId());
			clientComm.closeConnection();
			mainStage.hide();
		}
	}

	public void logout() {
		close();
	}

	public void resize() {
		AnchorPane.setBottomAnchor(statusAnchor, screenHeight * 0.7715);
		AnchorPane.setTopAnchor(menuAnchor, screenHeight * 0.2);
		AnchorPane.setRightAnchor(menuAnchor, screenWidth * 0.8);
		AnchorPane.setTopAnchor(workspaceAnchor, screenHeight * 0.2);
		AnchorPane.setLeftAnchor(workspaceAnchor, screenWidth * 0.2);
		AnchorPane.setRightAnchor(workspaceAnchor, screenWidth * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.2);
		AnchorPane.setLeftAnchor(optionsAnchor, screenWidth * 0.9);
		AnchorPane.setRightAnchor(avatarAnchor, screenWidth * 0.9);
		AnchorPane.setLeftAnchor(userData, screenWidth * 0.1);
		AnchorPane.setRightAnchor(userData, screenWidth * 0.8);
		AnchorPane.setLeftAnchor(stateAnchor, screenWidth * 0.01);
		avatar.setFitHeight(screenHeight * 0.2);
		avatar.setFitWidth(screenWidth * 0.1);
		stateButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		mapButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		reportButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		sessionButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		logoutButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
		helpButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
	}
}
