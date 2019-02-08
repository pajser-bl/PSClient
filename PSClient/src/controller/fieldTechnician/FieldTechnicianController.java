package controller.fieldTechnician;

import client.ClientCommunication;
import exception.MessageException;

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
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Event;
import model.FieldTechnician;
import model.Intervention;
import model.Session;
import utility.ChoiceBox;
import utility.InterventionHandler;
import utility.MessageBox;
import utility.TimeUtility;

public class FieldTechnicianController {

	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	private FieldTechnician user;
	private Intervention intervention;
	private Session session;
	private Stage mainStage;
	private InterventionHandler handler;
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
		this.session = new Session(user.getName() + " " + user.getLastName());
		session.userLogged();
	}

	public void newFieldReport(ActionEvent event) {
		if (intervention != null) {
			Stage reportStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/field_technician/RoadReportForm.fxml"));
			loader.setControllerFactory(e -> new RoadReportController(reportStage, this, clientComm, user, session, optionsAnchor,
					intervention, stateLabel, screenWidth * 0.3, screenHeight * 0.3));
			try {
				Parent root = loader.load();
				reportStage.setResizable(false);
				reportStage.getIcons().add(new Image("/resources/images/logo.png"));
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
		try {
			String oldState = user.getState();
			if (user.getState().equals("zauzet"))
				throw new MessageException("Zauzeto stanje se ne moze mijenjati");
			if (user.getState().equals("neaktivan")) {
				user.setState("aktivan");
				Platform.runLater(() -> {
					handler = new InterventionHandler(clientComm, this, user, session, intervention, optionsAnchor, stateLabel, stateImage,
							screenWidth, screenHeight);
					Thread thread = new Thread(handler);
					thread.setDaemon(true);
					thread.start();
				});
			} else
				user.setState("neaktivan");
			ArrayList<String> reply = clientComm.changeState(user.getId(), user.getState());
			if (reply.get(0).equals("CHANGE STATE OK")) {
				Platform.runLater(() -> {
					session.changeState(user.getState());
					stateLabel.setText("Stanje: " + user.getState());
					if (user.getState().equals("aktivan")) {
						stateImage.setImage(new Image("/resources/images/green_circle_available.png"));
						stateLabel.textFillProperty().setValue(Paint.valueOf("7db028"));
					}
					else {
						stateImage.setImage(new Image("/resources/images/red_circle_not_available.png"));
						stateLabel.textFillProperty().setValue(Paint.valueOf("ce1919"));
					}
				});
				MessageBox.displayMessage("Potvrda", "Stanje uspjesno promjenjeno");
			} else {
				user.setState(oldState);
				MessageBox.displayMessage("Greska", "Greska pri promjeni stanja");
			}
		} catch (MessageException e) {
			MessageBox.displayMessage("Greska", e.toString());
		}
	}
	
	public void showSession(ActionEvent event) {
		if (!workspaceAnchor.getChildren().isEmpty())
			workspaceAnchor.getChildren().clear();
		TextArea sessionTextArea = new TextArea();
		AnchorPane.setBottomAnchor(sessionTextArea, 10.0);
		AnchorPane.setTopAnchor(sessionTextArea, 10.0);
		AnchorPane.setLeftAnchor(sessionTextArea, 10.0);
		AnchorPane.setRightAnchor(sessionTextArea, 10.0);
		sessionTextArea.setText(session.toString());
		sessionTextArea.setEditable(false);
		sessionTextArea.getStylesheets().add(getClass().getResource("/css/text_area.css").toExternalForm());
		workspaceAnchor.getChildren().add(sessionTextArea);
	}
	
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
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
		stateImage.setFitHeight(screenHeight * 0.1);
		stateImage.setFitWidth(screenWidth * 0.05);
		stateButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		mapButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		reportButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		sessionButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		logoutButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
		helpButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
	}
}
