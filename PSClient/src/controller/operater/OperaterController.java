package controller.operater;

import utility.ChoiceBox;
import utility.ClientResources;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import client.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class OperaterController {

	private Session session;
	@FXML Button interventionsButton;
	@FXML Button mapButton;
	@FXML Button sessionButton;
	@FXML Button vehiclesButton;
	@FXML Button helpButton;
	@FXML Button logoutButton;
	@FXML BorderPane mainLayout;
	@FXML Label name;
	@FXML Label lastName;
	@FXML AnchorPane anchor;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		/*session = new Session();
		name.setText("Ime:" + resources.getUser().getName());
		lastName.setText("Prezime: " + resources.getUser().getLastName());
		resources.getStage().setOnCloseRequest(e -> {
			e.consume();
			close();
		});*/
	}
	
	public void showSession(ActionEvent event) {
		
	}
	
	public void showInterventions(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/operater/InterventionsForm.fxml"), resources);
			anchor.getChildren().add(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showVehicles(ActionEvent event) {
		
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
