package controller.operater;

import client.ClientCommunication;

import java.util.ArrayList;

import javafx.collections.FXCollections;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Event;
import model.FieldTechnician;
import model.Intervention;
import model.Request;
import model.Session;
import model.User;
import utility.ChoiceBox;
import utility.TimeUtility;
import utility.MessageBox;
import utility.PdfManager;

public class OperaterController {

	private double screenWidth;
	private double screenHeight;
	private User user;
	private ClientCommunication clientComm;
	private Session session;
	private Stage mainStage;
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
	@FXML BorderPane mainLayout;
	@FXML ImageView avatar;
	@FXML Label name;
	@FXML Label lastName;
	@FXML VBox userData;
	
	@FXML public void initialize() {
		session.getEventList().add(new Event("Korisnik " + user.getName() + " " + user.getLastName() + " se prijavio na sistem"));
		resize();
		name.setText("  " + user.getName());
		lastName.setText("  " + user.getLastName());
		mainStage.setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public OperaterController(Stage mainStage, ClientCommunication clientComm, User user, double screenWidth,
			double screenHeight) {
		this.mainStage = mainStage;
		this.clientComm = clientComm;
		this.user = user;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		session = new Session();
	}
	
	public void showInterventions(ActionEvent event) {
		ArrayList<String> reply = clientComm.showOpenedInterventions();
		ArrayList<Intervention> interventions = new ArrayList<>();
		if(reply.get(0).equals("VIEW OPENED INTERVENTIONS OK")) {
			for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsedReply = reply.get(i + 2).split(":");
				Intervention intervention = new Intervention(parsedReply[0], parsedReply[1], parsedReply[2],
						TimeUtility.stringToLocalDateTime(parsedReply[3].replace(";", ":")), parsedReply[4], parsedReply[5]);
				interventions.add(intervention);
			}
		}
		if(!workspaceAnchor.getChildren().isEmpty())
			workspaceAnchor.getChildren().clear();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/operater/InterventionsForm.fxml"));
			loader.setControllerFactory(e -> new InterventionsController(clientComm, user, 
					FXCollections.observableArrayList(interventions), session, screenWidth, screenHeight));
			Parent interventionsView = loader.load();
			workspaceAnchor.getChildren().add(interventionsView);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showFieldTechnicians(ActionEvent event) {
		Request request = new Request("VIEW FIELD TECHNICIANS", new ArrayList<String>());
		ArrayList<String> reply = clientComm.sendRequest(request);
		if(reply.get(0).equals("VIEW FIELD TECHNICIANS OK") && (Integer.parseInt(reply.get(1)) != 0)) {
			ArrayList<FieldTechnician> fieldTechnicians = new ArrayList<>();
			for (int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsedUser = reply.get(i + 2).split(":");
				fieldTechnicians.add(new FieldTechnician(parsedUser[0], parsedUser[1], parsedUser[2], parsedUser[3]));
			}
			if(!workspaceAnchor.getChildren().isEmpty())
					workspaceAnchor.getChildren().clear();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/operater/FieldTechnicianTableForm.fxml"));
				loader.setControllerFactory(e -> new FieldTechnicianTableController(fieldTechnicians));
				Parent techniciansView = loader.load();
				workspaceAnchor.getChildren().add(techniciansView);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (reply.get(0).equals("VIEW FIELD TECHNICIANS OK") && (Integer.parseInt(reply.get(1)) == 0)) {
			MessageBox.displayMessage("Greska", "Nema prijavljenih terenskih radnika");
		} else MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void showMap(ActionEvent event) {
		Stage mapStage = new Stage();
		mapStage.getIcons().add(new Image("/resources/images/logo.png"));
		mapStage.initModality(Modality.APPLICATION_MODAL);
		mapStage.setMaximized(true);
		mapStage.setResizable(false);
		WebView browser = new WebView();
		WebEngine  webEngine = browser.getEngine();
		webEngine.load("https://www.google.com/");
		mapStage.setScene(new Scene(browser));
		mapStage.show();
	}
	
	public void showSession(ActionEvent event) {
		if(!workspaceAnchor.getChildren().isEmpty())
			workspaceAnchor.getChildren().clear();
		TextArea sessionTextArea = new TextArea();
		sessionTextArea.setText(session.toString());
		AnchorPane.setBottomAnchor(sessionTextArea, 10.0);
		AnchorPane.setTopAnchor(sessionTextArea, 10.0);
		AnchorPane.setLeftAnchor(sessionTextArea, 10.0);
		AnchorPane.setRightAnchor(sessionTextArea, 10.0);
		sessionTextArea.setText(session.toString());
		sessionTextArea.setEditable(false);
		sessionTextArea.getStylesheets().add(getClass().getResource("/css/text_area.css").toExternalForm());
		workspaceAnchor.getChildren().add(sessionTextArea);
	}
	
	public void showHelp(ActionEvent event) {
		if(!PdfManager.readHelp())
			MessageBox.displayMessage("Greska", "Help.pdf nije pronadjen");
	}
	
	public void logout(ActionEvent logout) {
		close();
	}
	
	public void close() {
		boolean answer = ChoiceBox.displayChoice("Odjava", "Da li ste sigurni da zelite da se odjavite?");
		if(answer) {
			clientComm.logout(user.getUserId());
			clientComm.closeConnection();
			mainStage.hide();
		}
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
		interventionsButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		mapButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		sessionButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		vehiclesButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		logoutButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
		helpButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
	}
}
