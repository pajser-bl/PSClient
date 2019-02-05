package controller.fieldTechnician;

import client.ClientCommunication;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.FieldTechnician;
import model.Session;
import utility.ChoiceBox;
import utility.MessageBox;

public class FieldTechnicianController {

	private Session session;
	private Stage mainStage;
	private FieldTechnician user;
	private double screenHeight;
	private double screenWidth;
	private ClientCommunication clientComm;
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
	@FXML ImageView avatar;
	@FXML VBox userData;
	
	@FXML public void initialize() {
		resize();
		mainStage.setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public FieldTechnicianController(Stage mainStage, ClientCommunication clientComm, FieldTechnician user, double screenWidth,
			double screenHeight) {
		this.mainStage = mainStage;
		this.clientComm = clientComm;
		this.user = user;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		InterventionHandler handler = new InterventionHandler(mainStage, clientComm, user);
		
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
		ArrayList<String> reply = clientComm.changeState(user.getId(), user.getState());
		if(reply.get(0).equals("CHANGE STATE OK"))
				MessageBox.displayMessage("Potvrda", "Stanje uspjesno promjenjeno");
		else {
			if(user.getState().equals("aktivan")) {
				user.setState("neaktivan");
			} else user.setState("aktivan");
				MessageBox.displayMessage("Greska", "Greska pri promjeni stanja");
		}
	}
	
	public void showMap(ActionEvent event) {
		/*ClientResources reportResources = new ClientResources(null, resources.getClientCommunication(), resources.getUser(),
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
		}*/
	}
	
	public void close() {
		boolean answer = ChoiceBox.displayChoice("Odjava", "Da li ste sigurni da zelite da se odjavite?");
		if(answer) {
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
		avatar.setFitHeight(screenHeight * 0.8);
		avatar.setFitWidth(screenWidth * 0.1);
		stateButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		mapButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		reportButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		sessionButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		logoutButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
		refreshButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
		helpButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
	}
}
